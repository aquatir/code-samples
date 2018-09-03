package codesample.kotlin.jwtexample.dto.response

import org.springframework.http.HttpStatus

data class ExceptionResponse(
        val status: HttpStatus,
        val message: String
)