package codesample.kotlin.jwtexample.util

import codesample.kotlin.jwtexample.security.service.JwtTokenService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@Component
class TestUtils (private val jwtTokenService: JwtTokenService) {

    @Value("\${security.jwt.secret.access}")
    private lateinit var jwtAccessSecret: String

    @Value("\${security.jwt.secret.refresh}")
    private lateinit var jwtRefreshSecret: String

    fun generateAccessTokenForMills(userId: Long, mills: Long): String {
        return jwtTokenService.generateToken(userId.toString(), mills, jwtAccessSecret)
    }

    fun generateRefreshTokenForMills(userId: Long, mills: Long): String {
        return jwtTokenService.generateToken(userId.toString(), mills, jwtRefreshSecret)
    }


    companion object {
        fun getUrlWithToken( url: String, token: String) : MockHttpServletRequestBuilder {
            return MockMvcRequestBuilders.get(url).header("Authorization","Bearer $token")
        }

        fun asJsonString(obj: Any): String {
            try {
                return ObjectMapper().writeValueAsString(obj)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        fun <T> toObject(json: String, clazz: Class<T>) : T {
            return ObjectMapper().readValue(json, clazz)
        }
    }
}