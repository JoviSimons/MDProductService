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
        try{
            return categoryRepo.findById(id)
        }
        catch(e: Exception){
            throw BadRequestException("Category not found")
        }
    }

    //delete product
    fun deleteCategory(id: Int): String {
        try{
            categoryRepo.deleteById(id)
            return "Category deleted $id"
        }catch(e: Exception){
            throw BadRequestException("Category not found")
        }
    }

    //update user
    fun updateCategory(category: Category): Category {
        val existingCategory = categoryRepo.findById(category.id!!).orElse(null)
        existingCategory.name = category.name
        existingCategory.description = category.description
        return categoryRepo.save(existingCategory)
    }
}