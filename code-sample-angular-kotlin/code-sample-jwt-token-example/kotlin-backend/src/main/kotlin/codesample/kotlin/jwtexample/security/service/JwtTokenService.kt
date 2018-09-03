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

    @Value("\${security.jwt.secret.access}")
    private lateinit var jwtAccessSecret: String

    @Value("\${security.jwt.secret.refresh}")
    private lateinit var jwtRefreshSecret: String

    @Value("\${security.jwt.expirationInMs}")
    private lateinit var jwtAccessExpirationInMs: String

    @Value("\${security.jwt.refreshInMs}")
    private lateinit var jwtRefreshExpirationInMs: String

    fun generateAccessToken(authentication: Authentication) : String {
        val userPrincipal = authentication.principal as UserWithAuthorities
        return generateToken(userPrincipal.username, jwtAccessExpirationInMs.toLong(), jwtAccessSecret)
    }

    fun generateAccessToken(username: String) : String {
        return generateToken(username, jwtAccessExpirationInMs.toLong(), jwtAccessSecret)
    }

    fun generateRefreshToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserWithAuthorities
        return generateToken(userPrincipal.username, jwtRefreshExpirationInMs.toLong(), jwtRefreshSecret)
    }

    fun generateRefreshToken(username: String) : String {
        return generateToken(username, jwtRefreshExpirationInMs.toLong(), jwtRefreshSecret)
    }

    fun generateToken(username: String, expiresInMs: Long, secret: String) : String {
        val now = Date()
        val expiryDate = Date(now.time + expiresInMs)

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }


    fun getUsernameFromAccessJWT(token: String): String =
            getUsernameFromToken(token, jwtAccessSecret)


    fun getUsernameFromRefreshJWT(token: String): String =
            getUsernameFromToken(token, jwtRefreshSecret)

    private fun getUsernameFromToken(token: String, secret: String) : String =
        Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body
                .subject


    fun validateAccessToken(token: String): Boolean {
        return validateToken(token, jwtAccessSecret)
    }

    fun validateRefreshToken(token: String): Boolean {
        return validateToken(token, jwtRefreshSecret)
    }

    private fun validateToken(token: String, secret: String) : Boolean {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
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