package codesample.kotlin.jwtexample.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.util.*

@Component
class TestUtils {

    @Value("\${security.jwt.secret}")
    private lateinit var jwtSecret: String

    private fun generateTokenFor(userId: Long, issueDate: Date, expiryDate: Date): String =
        Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(issueDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()

    fun generateTokenForSeconds(userId: Long, seconds: Long): String {
        val today = Date()
        val expiryDate = Date(today.time + seconds * 1000)
        return generateTokenFor(userId, today, expiryDate)
    }

    companion object {
        fun getUrlWithToken( url: String, token: String) : MockHttpServletRequestBuilder {
            return MockMvcRequestBuilders.get(url).header("Authorization","Bearer $token")
        }
    }
}