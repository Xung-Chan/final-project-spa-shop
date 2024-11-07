package final_project_spa_shop.final_project_spa_shop.configuration;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import final_project_spa_shop.final_project_spa_shop.service.implementation.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LazySecurityContextProviderFilter extends OncePerRequestFilter {
	@Autowired
	AuthenticationService authenticationService;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {

		var context = SecurityContextHolder.getContext();
		SecurityContextHolder.setContext(new LazyJwtSecurityContextProvider(req, res, context));
		filterChain.doFilter(req, res);
	}

	static class LazyJwtSecurityContextProvider implements SecurityContext {

		private final HttpServletRequest req;
		private final HttpServletResponse res;
		private final SecurityContext securityCtx;
		@Autowired
		AuthenticationService authenticationService;

		public LazyJwtSecurityContextProvider(HttpServletRequest req, HttpServletResponse res,
				SecurityContext securityCtx) {
			this.req = req;
			this.res = res;
			this.securityCtx = securityCtx;
		}

		@Override
		public Authentication getAuthentication() {
			if (securityCtx.getAuthentication() == null || securityCtx.getAuthentication().isAuthenticated()) {
				String token = extractJwtFromRequest(req);
				System.out.println( token);
				if (token != null) {
					// Use the AuthenticationService to authenticate based on the JWT token
					Authentication auth = authenticationService.authenticateWithToken(token);
					securityCtx.setAuthentication(auth);
				}
			}
			return securityCtx.getAuthentication();
		}

		@Override
		public void setAuthentication(Authentication authentication) {
			securityCtx.setAuthentication(authentication);
		}

		private String extractJwtFromRequest(HttpServletRequest request) {
//            String header = request.getHeader("Authorization");
//            if (header != null && header.startsWith("Bearer ")) {
//                return header.substring(7);
//            }
//            return null;
        	var token = Arrays.stream( request.getCookies()).filter(e -> e.getName().equals("token"))
                    .findFirst()
                    .orElseThrow();
        	return token.getValue();
        }
	}
}
