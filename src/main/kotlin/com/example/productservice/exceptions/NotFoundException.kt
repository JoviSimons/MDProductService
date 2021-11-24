package com.example.productservice.exceptions

import org.springframework.http.HttpStatus

class NotFoundException(msg: String?) : RuntimeException(msg){
    val status: HttpStatus = HttpStatus.NOT_FOUND
}