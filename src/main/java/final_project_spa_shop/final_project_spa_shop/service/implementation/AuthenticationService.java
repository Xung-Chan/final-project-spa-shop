package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@NonFinal
	@Value("${spa-shop.signer-key}")
	String key ;

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		Optional<AccountEntity> optional = accountRepository.findByUsername(request.getUsername());
		if (!optional.isPresent())
			throw new RuntimeException(ErrorCode.LOGIN_FAILURE.name());
		AccountEntity accountEntity = optional.get();

		PasswordEncoder encoder = new BCryptPasswordEncoder(9);
		if(!encoder.matches(request.getPassword(), accountEntity.getPassword()))
			throw new RuntimeException(ErrorCode.LOGIN_FAILURE.name());
		
		
		return AuthenticationResponse.builder()
				.token(generateToken(accountEntity
						.getUsername()))
				.authenticated(true)
				.id(accountEntity.getId())
				.fullName(accountEntity.getFullName())
				.build();
	}
	
	public String generateToken(String username) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject(username)
				.issuer("spa-shop")
				.issueTime(new Date())
				.expirationTime(new Date(Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()))
				.claim("role","customer")
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
