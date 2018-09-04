package codesample.kotlin.jwtexample.dto.request

data class AccessTokenByRefreshTokenRequest(
        val refreshToken: String
)