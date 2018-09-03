package codesample.kotlin.jwtexample.dto.response

// Response with access token
data class AccessTokenResponse (
        val accessToken: String
) {
    /* Used to create AccessTokenResponse in tests */
    constructor() : this("")
}