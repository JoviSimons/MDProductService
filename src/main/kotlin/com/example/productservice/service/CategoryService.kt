package com.example.productservice.service

import com.example.productservice.exceptions.BadRequestException
import com.example.productservice.model.Category
import com.example.productservice.repo.CategoryRepo
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

@AllArgsConstructor
@Service
class CategoryService {
    var categoryRepo: CategoryRepo? = null

    fun getAllCategories(): List<Category?>? {
        return categoryRepo?.findAll()
    }

    fun addCategory(category: Category) {
        val existsName: Boolean? = categoryRepo?.selectExistsName(category.name)
        if (existsName == true) {
            throw BadRequestException("Category name '" + category.name.toString() + "' taken")
        }
        categoryRepo?.save(category)
    }
}