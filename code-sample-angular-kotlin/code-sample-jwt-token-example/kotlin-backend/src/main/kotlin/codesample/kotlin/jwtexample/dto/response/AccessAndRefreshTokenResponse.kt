package codesample.kotlin.jwtexample.dto.response

data class AccessAndRefreshTokenResponse (
        val accessToken: String,
        val refreshToken: String
) {
    /* Used to create AccessAndRefreshTokenResponse in tests */
    constructor() : this("", "")
}