package com.tmp.BTS.exception

import com.tmp.BTS.exception.ErrorResponse
import com.tmp.BTS.exception.BTSException
import net.logstash.logback.argument.StructuredArguments.kv
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class BTSExceptionHandler : ResponseEntityExceptionHandler() {
    private val log = org.slf4j.LoggerFactory.getLogger(BTSExceptionHandler::class.java)

    @ExceptionHandler(NotFoundException::class)
    fun NotFoundException(ex :NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val exception = (ex as BTSException)
        log.error(exception.message, kv("eventCode", exception.logEventCode), kv("backTrace", exception.stackTrace[0].toString()))
        val errorResponse = ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), exception.code.message, exception.code.status)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(InvalidParameterException::class)
    fun InvalidParameterException(ex :InvalidParameterException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val exception = (ex as BTSException)
        log.error(exception.message, kv("eventCode", exception.logEventCode), kv("backTrace", exception.stackTrace[0].toString()))
        val errorResponse = ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), exception.code.message, exception.code.status)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(InternalException::class)
    fun InternalException(ex :InternalException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val exception = (ex as BTSException)
        log.error(exception.message, kv("eventCode", exception.logEventCode), kv("backTrace", exception.stackTrace[0].toString()))
        val errorResponse = ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.code.message, exception.code.status)
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(BadRequestException::class)
    fun BadRequestException(ex :BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val exception = (ex as BTSException)
        log.error(exception.message, kv("eventCode", exception.logEventCode), kv("backTrace", exception.stackTrace[0].toString()))
        val errorResponse = ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), exception.code.message, exception.code.status)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UnAuthorizeException::class)
    fun UnAuthorizeException(ex :UnAuthorizeException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val exception = (ex as BTSException)
        log.error(exception.message, kv("eventCode", exception.logEventCode), kv("backTrace", exception.stackTrace[0].toString()))
        val errorResponse = ErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), exception.code.message, exception.code.status)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }
}
