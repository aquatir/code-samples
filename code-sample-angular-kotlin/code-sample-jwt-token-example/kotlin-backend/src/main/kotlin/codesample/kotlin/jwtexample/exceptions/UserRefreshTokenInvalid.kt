package codesample.kotlin.jwtexample.exceptions

import io.jsonwebtoken.JwtException

class UserRefreshTokenInvalid(message: String): JwtException (message)