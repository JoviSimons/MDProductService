package com.example.productservice.category

import com.example.productservice.model.Category
import com.example.productservice.repo.CategoryRepo
import com.example.productservice.service.CategoryService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CategoryServiceTest {
    @Mock
    private val categoryRepo: CategoryRepo? = null
    private var autoCloseable: AutoCloseable? = null
    private var underTest: CategoryService? = null

    //before start of every test
    @BeforeEach
    fun setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this)
        underTest = CategoryService(categoryRepo!!)
    }

    //end of every test
    @AfterEach
    @Throws(Exception::class)
    fun tearDown() {
        autoCloseable!!.close()
    }

    @Test
    fun saveCategory() {
        val cat = Category()
        //when
        underTest!!.saveCategory(cat)
        //then
        verify(categoryRepo)?.save(cat)
    }

    @Test
    fun category(){
            //when
            underTest?.categories
            //then
            verify(categoryRepo)?.findAll()
        }

    @Test
    fun categoryById()
         {
            //when
            underTest!!.getCategoryById(1)
            //then
            verify(categoryRepo)?.findById(1)
        }

    @Test
    fun deleteProduct() {
        //when
        underTest!!.deleteCategory(1)
        //then
        verify(categoryRepo)?.deleteById(1)
    }
}