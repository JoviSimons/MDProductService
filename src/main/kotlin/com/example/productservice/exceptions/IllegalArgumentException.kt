package com.example.productservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class IllegalArgumentException(msg: String?) : RuntimeException(msg)