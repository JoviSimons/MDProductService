package com.example.productservice

import com.example.productservice.controller.CategoryController
import org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CategoryUnitTest {
    @InjectMocks
    lateinit var categoryController: CategoryController
    @Test
    fun testGetAll() {
        val result = categoryController.all()
        assertNotNull(result)
    }
    @Test
    fun testGetById() {
        val result = categoryController.one(1)
        assertNotNull(result)
    }
}