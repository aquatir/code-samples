package codesample.kotlin.jwtexample.config

import codesample.kotlin.jwtexample.dto.response.ExceptionResponse
import io.jsonwebtoken.JwtException
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
    fun handleBadCredentialsException(request: HttpServletRequest, ex: BadCredentialsException) : ExceptionResponse {
        return ExceptionResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED")
    }

    @ExceptionHandler(JwtException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    fun handleJwtExceptions(request: HttpServletRequest, ex: JwtException) : ExceptionResponse {
        return ExceptionResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED")
    }
}