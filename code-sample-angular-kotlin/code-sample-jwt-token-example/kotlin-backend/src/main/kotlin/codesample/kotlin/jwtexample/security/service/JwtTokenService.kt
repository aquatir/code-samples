package codesample.kotlin.jwtexample.security.service

import codesample.kotlin.jwtexample.security.model.UserWithAuthorities
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*


@Service
class JwtTokenService {
    private val logger = LoggerFactory.getLogger(JwtTokenService::class.java)

    @Value("#{security.jwt.secret}")
    private lateinit var jwtSecret: String

    @Value("#{security.jwt.expirationInMs}")
    private lateinit var jwtExpirationInMs: String

    fun generateToken(authentication: Authentication) :String {
        val userPrincipal = authentication.principal as UserWithAuthorities

        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs.toLong())

        return Jwts.builder()
                .setSubject(java.lang.Long.toString(userPrincipal.id))
                .setIssuedAt(Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    fun getUserIdFromJWT(token: String): Long =
        Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body
                .subject
                .toLong()

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }

        return false
    }
}