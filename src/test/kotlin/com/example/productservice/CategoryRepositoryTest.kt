package com.example.productservice


import com.example.productservice.model.Category
import com.example.productservice.repo.CategoryRepo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private val repo: CategoryRepo? = null

    @AfterEach
    fun tearDown() {
        repo?.deleteAll()
    }

    @Test
    fun itShouldSaveCategoryCorrectly() {
        // given
        val categoryId = 1
        val category = Category()
        category.id = categoryId
        category.name = "test"
        category.description = "description"

        repo?.save(category)

        // when
        val expected: Category? = repo?.getById(categoryId)

        // then
        assertEquals(expected?.id, category.id)
        assertEquals(expected?.name, category.name)
        assertEquals(expected?.description, category.description)

    }

    @Test
    fun itShouldEditCategoryCorrectly() {
        // given
        val id = 1
        val category = Category()
        category.id = id
        category.name = "test"
        category.description = "description"
        repo?.save(category)

        val newCategory = Category()
        newCategory.name = "edit"
        newCategory.description = "test"


        // when
        val expected: Category? = repo?.getById(id)

        // then
        assertEquals(expected?.id, newCategory.id)
        assertEquals(expected?.name, newCategory.name)
        assertEquals(expected?.description, newCategory.description)

    }

    @Test
    fun itShouldDeleteCorrectCategory() {
        // given
        val categoryId = 1
        val category = Category()
        category.id = categoryId
        category.name = "test"
        category.description = "description"

        repo?.save(category)

        // when
        val expected: Category? = repo?.getById(categoryId)

        // then
        assertEquals(expected?.id, category.id)
        assertEquals(expected?.name, category.name)
        assertEquals(expected?.description, category.description)

    }

}