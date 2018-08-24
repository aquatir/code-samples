package codesample.kotlin.jwtexample.config

import codesample.kotlin.jwtexample.dto.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandlingConfig {

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    fun handleBadCredentials(request: HttpServletRequest, ex: BadCredentialsException) : ExceptionResponse {
        return ExceptionResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED")
    }
}