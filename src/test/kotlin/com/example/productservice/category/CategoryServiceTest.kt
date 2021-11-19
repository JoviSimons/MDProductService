package com.example.productservice.category

import com.example.productservice.exceptions.BadRequestException
import com.example.productservice.model.Category
import com.example.productservice.repo.CategoryRepo
import com.example.productservice.service.CategoryService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.BDDAssumptions.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CategoryServiceTest {

    @Mock
    private val categoryRepository: CategoryRepo? = null

    private var service: CategoryService? = null

    @BeforeEach
    fun setUp() {
        service = CategoryService()
        service!!.categoryRepo = categoryRepository
    }

    @Test
    fun canGetAllStudents() {
        // when
        service?.getAllCategories()
        // then
        verify(categoryRepository)?.findAll()
    }

    @Test
    fun canAddStudent() {
        // given
        val category = Category()
        category.name = "name"
        category.description = "description"

        // when
        service?.addCategory(category)

        // then
        val categoryArgumentCaptor: ArgumentCaptor<Category> = ArgumentCaptor.forClass(Category::class.java)
        verify(categoryRepository)?.save(categoryArgumentCaptor.capture())
        val capturedCategory: Category = categoryArgumentCaptor.value
        assertThat(capturedCategory).isEqualTo(category)
    }

}