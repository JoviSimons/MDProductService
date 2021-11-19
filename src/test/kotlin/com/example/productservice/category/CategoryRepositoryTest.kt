package com.example.productservice.category


import com.example.productservice.model.Category
import com.example.productservice.repo.CategoryRepo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
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
    fun itShouldCheckWhenStudentEmailExists() {
        // given
        val name = "testName"
        val category = Category()
        category.name = name
        category.description = "description"
        repo?.save(category)

        // when
        val expected: Boolean? = repo?.selectExistsName(name)

        // then
        assertThat(expected).isTrue
    }

    @Test
    fun itShouldCheckWhenStudentEmailDoesNotExists() {
        // given
        val name = "testName"

        // when
        val expected: Boolean? = repo?.selectExistsName(name)

        // then
        assertThat(expected).isFalse
    }

}