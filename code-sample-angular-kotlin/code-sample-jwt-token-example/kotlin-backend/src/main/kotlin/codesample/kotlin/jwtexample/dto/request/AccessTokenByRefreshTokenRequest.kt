package codesample.kotlin.jwtexample.dto.request

// Request for getting new access token using refresh token
data class AccessTokenByRefreshTokenRequest(
        val refreshToken: String
)