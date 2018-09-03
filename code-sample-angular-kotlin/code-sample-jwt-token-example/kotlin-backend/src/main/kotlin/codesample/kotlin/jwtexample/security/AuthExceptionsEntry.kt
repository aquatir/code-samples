package codesample.kotlin.jwtexample.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * This class will handle errors with authorization.
 * Usually spring security will redirect user to login page when user can not be authenticated.
 * We are building SPA with JWT token, so unauthorized access should be handled manually by application
 */
@Component
class AuthExceptionsEntry: AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          authException: AuthenticationException) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
    }

}