package com.example.productservice.repo

import com.example.productservice.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CategoryRepo : JpaRepository<Category, Int>{
    @Query("SELECT CASE " +
            "WHEN COUNT(c) > 0 " +
            "THEN TRUE ELSE FALSE END" +
            " FROM Category c WHERE c.name = ?1")
    fun selectExistsName(name: String?): Boolean?
}