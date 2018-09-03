package codesample.kotlin.jwtexample.security.filter

import codesample.kotlin.jwtexample.security.service.DbUserDetailsService
import codesample.kotlin.jwtexample.security.service.JwtTokenService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthFilter(private val jwtTokenService: JwtTokenService,
                    private val dbUserDetailsService: DbUserDetailsService): OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jwtToken = getJwtFromRequest(request)
            if (jwtToken != null && jwtTokenService.validateAccessToken(jwtToken)) {
                val username = jwtTokenService.getUsernameFromAccessJWT(jwtToken)
                val userDetails = dbUserDetailsService.loadUserByUsername(username)

                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                        .apply { details = WebAuthenticationDetailsSource().buildDetails(request) }
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (ex: Exception ) {
            logger.error("Could not set user authentication in security context", ex)
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else {
            null
        }
    }
}