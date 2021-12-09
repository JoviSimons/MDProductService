package com.example.productservice.repo

import com.example.productservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProductRepo : JpaRepository<Product, Int>{

    @Query("from Product p inner join fetch p.category where p.category.id = :id")
    fun findByCategoryId(@Param("id") id: Int): List<Product>

}