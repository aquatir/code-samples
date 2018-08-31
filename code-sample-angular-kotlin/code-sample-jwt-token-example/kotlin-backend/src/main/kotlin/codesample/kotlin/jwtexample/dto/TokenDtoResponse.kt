package codesample.kotlin.jwtexample.dto

data class TokenDtoResponse (
        var accessToken: String,
        var refreshToken: String
) {
    /* User to create TokenDtoResponse in tests */
    constructor() : this("", "")
}