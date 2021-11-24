package com.example.productservice.category

import com.example.productservice.model.Category
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class ControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    val baseUrl = "/MyDrugs/categories"

    @Nested
    @DisplayName("GET /MyDrugs/categories")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetCategories {

        @Test
        fun `should return all categories`() {
            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }

    }

    @Nested
    @DisplayName("GET /MyDrugs/categories/{id}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetCategory {

        @Test
        fun `should return the category with the given id`() {
            // given
            val category = 1

            // when/then
            mockMvc.get("$baseUrl/$category")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }

            @Test
            fun `should return NOT FOUND if the category does not exist`() {
                // given
                val category = 20

                // when/then
                mockMvc.get("$baseUrl/$category")
                    .andDo { print() }
                    .andExpect { status { isNotFound() } }
            }
    }

    @Nested
    @DisplayName("POST /MyDrugs/categories")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewCategory {

        @Test
        fun `should add the new category`() {
            // given
            val newCategory = Category(1, "category", "description")

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newCategory)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newCategory))
                    }
                }

            mockMvc.get("$baseUrl/${newCategory.id}")
                .andExpect { content { json(objectMapper.writeValueAsString(newCategory)) } }
        }

            @Test
            fun `should return error message if category with same name exists`() {
                // given
                val invalidCategory = Category(3, "Cat", "description")

                // when
                val performPost = mockMvc.post(baseUrl) {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(invalidCategory)
                }

                // then
                performPost
                    .andDo { print() }
                    .andExpect {
                        jsonPath("$.message"){ value("Category name already exists")}
                    }

            }


    }

    @Nested
    @DisplayName("Put /MyDrugs/categories")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PutExistingCategory {

        @Test
        fun `should update an existing category`() {
            // given
            val updatedCategory = Category(1, "update", "category")

            // when
            val performPutRequest = mockMvc.put("$baseUrl/${updatedCategory.id}") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedCategory)
            }

            // then
            performPutRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedCategory))
                    }
                }

            mockMvc.get("$baseUrl/${updatedCategory.id}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedCategory)) } }
        }

        @Test
        fun `should return NOT FOUND if no category with given id exists`() {
            // given
            val updatedCategory = Category(10, "update", "category")

            // when
            val performPutRequest = mockMvc.put("$baseUrl/${updatedCategory.id}") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedCategory)
            }

            // then
            performPutRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return error message if category with same name exists`() {
            // given
            val invalidCategory = Category(2, "Category", "description")

            // when
            val performPost = mockMvc.put("$baseUrl/${invalidCategory.id}") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidCategory)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    jsonPath("$.message"){ value("Category name already exists")}
                }

        }
    }

    @Nested
    @DisplayName("DELETE /MyDrugs/categories/{id}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingCategory {

        @Test
        fun `should delete the category with the given id`() {
            // given
            val categoryId = 2

            // when/then
            mockMvc.delete("$baseUrl/$categoryId")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                }

            mockMvc.get("$baseUrl/$categoryId")
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return NOT FOUND if no category with given id exists`() {
            // given
            val categoryId = 10

            // when/then
            mockMvc.delete("$baseUrl/$categoryId")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}