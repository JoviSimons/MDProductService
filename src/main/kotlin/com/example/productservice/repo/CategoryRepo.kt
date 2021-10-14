package com.example.productservice.repo

import com.example.productservice.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepo : JpaRepository<Category, Int>