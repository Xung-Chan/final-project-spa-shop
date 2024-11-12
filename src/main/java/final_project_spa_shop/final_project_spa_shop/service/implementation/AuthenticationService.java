package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import final_project_spa_shop.final_project_spa_shop.dto.request.AuthenticationRequest;
import final_project_spa_shop.final_project_spa_shop.dto.request.IntrospectRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AuthenticationResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.IntrospectResponse;
import final_project_spa_shop.final_project_spa_shop.entity.AccountEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.repository.AccountRepository;
import final_project_spa_shop.final_project_spa_shop.service.IAuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
	AccountRepository accountRepository;
	PasswordEncoder encoder;
	@NonFinal
	@Value("${spa-shop.signer-key}")
	String key ;

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		Optional<AccountEntity> optional = accountRepository.findByUsername(request.getUsername());
		if (!optional.isPresent())
			throw new RuntimeException(ErrorCode.LOGIN_FAILURE.name());
		AccountEntity accountEntity = optional.get();

		if(!encoder.matches(request.getPassword(), accountEntity.getPassword()))
			throw new RuntimeException(ErrorCode.LOGIN_FAILURE.name());
		return AuthenticationResponse.builder()
				.token(generateToken(accountEntity))
				.authenticated(true)
				.role(accountEntity.getRole().getName())
				.build();
	}
	
	public String generateToken(AccountEntity entity) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject(entity.getUsername())
				.issuer("spa-shop")
				.issueTime(new Date())
				.expirationTime(new Date(Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()))
				.claim("scope",entity.getRole().getName())
				.build();
		Payload payload = new Payload(claimsSet.toJSONObject());
		JWSObject jwsObject = new JWSObject(header, payload);
		try {
			jwsObject.sign(new MACSigner(key.getBytes()));
			return jwsObject.serialize();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public Authentication authenticateWithToken(String token) {
	    try {
	        // Xác thực và giải mã token JWT
	        JWSVerifier jwsVerifier = new MACVerifier(key.getBytes());
	        SignedJWT signedJWT = SignedJWT.parse(token);
	        
	        // Kiểm tra token hợp lệ
	        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
	        if (expiryTime.before(new Date())) {
	            throw new RuntimeException("Token đã hết hạn");
	        }

	        // Kiểm tra tính hợp lệ của token
	        if (!signedJWT.verify(jwsVerifier)) {
	            throw new RuntimeException("Token không hợp lệ");
	        }

	        // Lấy thông tin từ claims trong JWT
	        String username = signedJWT.getJWTClaimsSet().getSubject();
	        String role = signedJWT.getJWTClaimsSet().getStringClaim("scope"); // Giả sử "scope" lưu trữ vai trò của người dùng

	        // Tạo danh sách quyền từ token
	        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

	        // Tạo đối tượng Authentication với thông tin người dùng và quyền
	        return new UsernamePasswordAuthenticationToken(username, null, authorities);
	    } catch (Exception e) {
	        throw new RuntimeException("Không thể xác thực token: " + e.getMessage());
	    }
	}
	@Override
	public IntrospectResponse introspect(IntrospectRequest request) {
		String token = request.getToken();
		try {
			JWSVerifier jwsVerifier = new MACVerifier(key.getBytes());
			SignedJWT signedJWT = SignedJWT.parse(token);
			Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
			return IntrospectResponse.builder()
					.valid(signedJWT.verify(jwsVerifier) && expiryTime.after(new Date()))
					.build();
		} catch (Exception e) {
			throw new RuntimeException(ErrorCode.JWT_EXCEPTION.name());
		}
		
	}

}
