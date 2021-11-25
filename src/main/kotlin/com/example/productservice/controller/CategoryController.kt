package com.example.productservice.controller

import com.example.productservice.exceptions.BadRequestException
import com.example.productservice.exceptions.IllegalArgumentException
import com.example.productservice.exceptions.NotFoundException
import com.example.productservice.model.Category
import com.example.productservice.service.CategoryService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/MyDrugs")
@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
class CategoryController(categoryService: CategoryService) {
    private val service: CategoryService

    @GetMapping("/categories")
    fun all(): List<Category> = service.categories


    @PostMapping("/categories")
    fun newCategory(@RequestBody newCategory: Category): Category {
        if(service.nameExists(newCategory) == true) {
            throw BadRequestException("Category name already exists")
        }
        return service.saveCategory(newCategory)!!
    }

    // Single item
    @GetMapping("/categories/{id}")
    fun one(@PathVariable id: Int): Optional<Category> {
        val optCat: Optional<Category>  = service.getCategoryById(id)
        if (optCat.isPresent){
            return optCat
        } else {
            throw NotFoundException("Category not found")
        }
    }

    @PutMapping("/categories/{id}")
    fun replaceCategory(@RequestBody newCategory: Category, @PathVariable id: Int): Category {
        val optCat: Optional<Category>  = service.getCategoryById(id)
        if (optCat.isPresent){
            if(service.nameExists(newCategory) == true)
                throw IllegalArgumentException("Category name already exists")
            return service.updateCategory(newCategory)
        } else {
            throw NotFoundException("Category not found")
        }
    }

    @DeleteMapping("/categories/{id}")
    fun deleteCategory(@PathVariable id: Int): String {
        val optCat: Optional<Category>  = service.getCategoryById(id)
        if (optCat.isPresent){
            return service.deleteCategory(id)
        } else {
            throw NotFoundException("Category not found")
        }
    }

    init {
        this.service = categoryService
    }
}