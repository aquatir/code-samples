package codesample.kotlin.jwtexample.dto

data class TokenDto (
        var accessToken: String,
        var refreshToken: String
) {
    /* User to create TokenDto in tests */
    constructor() : this("", "")
}