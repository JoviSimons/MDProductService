package com.example.productservice.controller

import com.example.productservice.model.Category
import net.minidev.json.JSONObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryIntegrationTest(@Autowired val testRestTemplate: TestRestTemplate) {

    lateinit var getCategoryUrl: String
    lateinit var categoryUrl: String
    lateinit var headers: HttpHeaders
    lateinit var categoryPostObject: JSONObject
    lateinit var categoryPutObject: JSONObject
    var categoryId: Int? = null

    @BeforeAll
    fun setup () {
        getCategoryUrl = "/MyDrugs/categories/"
        categoryUrl = "/MyDrugs/categories/"
        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        categoryPutObject = JSONObject()
        categoryPutObject["name"] = "Doe"
        categoryPutObject["description"] = "John"

        categoryPostObject = JSONObject()
        categoryPostObject["name"] = "John"
        categoryPostObject["description"] = "Doe"
    }

    @Test
    fun testCategoryControllerGetById() {
        val result = testRestTemplate.getForEntity(getCategoryUrl+"1", Category::class.java)
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    fun testCategoryControllerPost() {

        val request = HttpEntity<String>(categoryPostObject.toString(), headers)

        val category: Category = testRestTemplate.postForObject(categoryUrl, request, Category::class.java)

        assertNotNull(category)
        assertNotNull(category.id)
        categoryId = category.id
        assertNotNull(category.name)
        assertNotNull(category.description)
        assertEquals(categoryPostObject["name"],category.name)
        assertEquals(categoryPostObject["description"],category.description)

        testRestTemplate.delete(categoryUrl+categoryId.toString())

    }

    @Test
    fun testCategoryControllerPut() {

        var request = HttpEntity<String>(categoryPostObject.toString(), headers)

        val postCategory: Category = testRestTemplate.postForObject(categoryUrl, request, Category::class.java)

        assertNotNull(postCategory)
        assertNotNull(postCategory.id)
        categoryId = postCategory.id

         request = HttpEntity<String>(categoryPutObject.toString(), headers)

        val result = testRestTemplate.put(categoryUrl+categoryId.toString(), request)

        assertNotNull(result)

        val category: Category = testRestTemplate.getForObject(getCategoryUrl+categoryId.toString(), Category::class.java)

        assertEquals(categoryPutObject["name"],category.name)
        assertEquals(categoryPutObject["description"],category.description)

        testRestTemplate.delete(categoryUrl+categoryId.toString())
    }

    @Test
    fun testCategoryControllerDelete() {
           // testRestTemplate.delete(categoryUrl+categoryId.toString())

        val result = testRestTemplate.getForEntity(getCategoryUrl+"10", Category::class.java)
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

}