package com.example.productservice.service

import com.example.productservice.exceptions.BadRequestException
import com.example.productservice.model.Category
import com.example.productservice.repo.CategoryRepo
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService(private val categoryRepo: CategoryRepo) {
    //post product
    fun saveCategory(category: Category): Category? {
        return categoryRepo.save(category)
    }

    //get products
    val categories: List<Category>
        get() = categoryRepo.findAll()

    //get product by id
    fun getCategoryById(id: Int): Optional<Category> {
            return categoryRepo.findById(id)
    }

    //delete product
    fun deleteCategory(id: Int): String {
            categoryRepo.deleteById(id)
            return "Category deleted $id"
    }

    //update user
    fun updateCategory(category: Category): Category {
        val existingCategory = categoryRepo.findById(category.id!!).orElse(null)
        existingCategory.name = category.name
        existingCategory.description = category.description
        return categoryRepo.save(existingCategory)
    }

    fun nameExists(category: Category): Boolean?{
        return categoryRepo.selectExistsName(category.name)
    }
}