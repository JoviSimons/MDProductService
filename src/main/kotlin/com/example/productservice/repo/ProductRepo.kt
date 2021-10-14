package com.example.productservice.repo

import com.example.productservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepo : JpaRepository<Product, Int>