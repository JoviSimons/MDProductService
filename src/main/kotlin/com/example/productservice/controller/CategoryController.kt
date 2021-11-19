package com.example.productservice.controller

import com.example.productservice.model.Category
import com.example.productservice.repo.CategoryRepo
import com.example.productservice.service.CategoryService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/MyDrugs")
@CrossOrigin(origins = arrayOf("http://localhost:3000"))
@RestController
class CategoryController(repository: CategoryRepo) {
    private val repo: CategoryRepo
    private val categoryService: CategoryService? = null

    @GetMapping("/categories")
    fun all(): List<Category?>? {
        return categoryService?.getAllCategories()
    }

    @PostMapping("/categories")
    fun newCategory(@RequestBody newCategory: Category): Unit? {
        return categoryService?.addCategory(newCategory)
    }

    // Single item
    @GetMapping("/categories/{id}")
    fun one(@PathVariable id: Int): Optional<Category> {
        return repo.findById(id)
    }

    @PutMapping("/categories/{id}")
    fun replaceCategory(@RequestBody newCategory: Category, @PathVariable id: Int): Category {
        return repo.findById(id)
                .map { category ->
                    category.name = newCategory.name
                    category.description = newCategory.description
                    repo.save(category)
                }
                .orElseGet {
                    repo.save(newCategory)
                }
    }

    @DeleteMapping("/categories/{id}")
    fun deleteCategory(@PathVariable id: Int) {
        repo.deleteById(id)
    }

    init {
        this.repo = repository
    }
}