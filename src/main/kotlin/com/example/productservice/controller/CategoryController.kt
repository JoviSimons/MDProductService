package com.example.productservice.controller

import com.example.productservice.exceptions.BadRequestException
import com.example.productservice.model.Category
import com.example.productservice.repo.CategoryRepo
import com.example.productservice.service.CategoryService
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.util.*

@RequestMapping("/MyDrugs")
@CrossOrigin(origins = arrayOf("http://localhost:3000"))
@RestController
class CategoryController(categoryService: CategoryService) {
    private val service: CategoryService

    @GetMapping("/categories")
    fun all(): List<Category> {
        return service.categories
    }

    @PostMapping("/categories")
    fun newCategory(@RequestBody newCategory: Category): Category {
        return service.saveCategory(newCategory)!!
    }

    // Single item
    @GetMapping("/categories/{id}")
    fun one(@PathVariable id: Int): Optional<Category> {
        try{
            return service.getCategoryById(id)
        }catch(e: Exception){
            throw BadRequestException("Category not found")
        }
    }

    @PutMapping("/categories/{id}")
    fun replaceCategory(@RequestBody newCategory: Category, @PathVariable id: Int): Category {
        return service.updateCategory(newCategory)
    }

    @DeleteMapping("/categories/{id}")
    fun deleteCategory(@PathVariable id: Int): String {
        try{
            return service.deleteCategory(id)
        }catch(e: Exception){
            throw BadRequestException("Category not found")
        }
    }

    init {
        this.service = categoryService
    }
}