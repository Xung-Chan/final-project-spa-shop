package final_project_spa_shop.final_project_spa_shop.configuration;

import java.io.IOException;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class SecurityConfig {
	@NonFinal
	@Value("${spa-shop.signer-key}")
	String key;
	JwtCookieToHeaderFilter cookieToHeaderFilter;
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		String[] publicPostEndpoints= {"/auth/**","/customer"}; 
		String[] publicGetEndpoints= {"/customer/home","/service/limit","/employee/limit","/feedback/feedbacks","/customer/login","/customer/css/**"
				,"/customer/js/**","/customer/img/**","/customer/lib/**","/customer/registry"}; 
		httpSecurity.addFilterBefore(cookieToHeaderFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.authorizeHttpRequests(
				request -> request
				.requestMatchers(HttpMethod.POST, publicPostEndpoints)
				.permitAll()
				.requestMatchers(HttpMethod.GET,publicGetEndpoints)
				.permitAll()
				.anyRequest()
				.authenticated()
//				.permitAll()
				);
		httpSecurity.oauth2ResourceServer(oauth2->
				oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()).jwtAuthenticationConverter(authenticationConverter())));
		httpSecurity.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				request.getRequestDispatcher("/customer/login").forward(request, response);
				
			}
		});
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		return httpSecurity.build();
	}
	@Bean
	JwtDecoder jwtDecoder() {
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "HS512");
		return NimbusJwtDecoder
				.withSecretKey(keySpec)
				.macAlgorithm(MacAlgorithm.HS512).build();
	}
	@Bean
	JwtAuthenticationConverter authenticationConverter() {
		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		return converter;
	}
	@Bean 
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(9);
	}
}
