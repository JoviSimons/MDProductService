package com.example.productservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler
    fun handleNotFoundException(exc : NotFoundException) : ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            exc.status.value(),
            exc.message,
            System.currentTimeMillis())

        return ResponseEntity(errorResponse, exc.status)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exc : IllegalArgumentException) : ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            HttpStatus.CONFLICT.value(),
            exc.message,
            System.currentTimeMillis())

        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exc : BadRequestException) : ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            exc.message,
            System.currentTimeMillis())

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleGenericException(exc : Exception) : ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST.value(),
            exc.message,
            System.currentTimeMillis())

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}