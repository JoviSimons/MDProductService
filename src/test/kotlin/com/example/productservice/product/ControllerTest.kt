package com.example.productservice.product

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
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class ControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    val baseUrl = "/MyDrugs/products"

    @Nested
    @DisplayName("As a drugs user, I want to see all drugs so that I can buy drugs ")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetDrugs {

        @Test
        fun `should return the products ordered by given category`() {
            // given
            val category = 1

            // when/then
            mockMvc.get("$baseUrl/category/$category")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }

            @Test
            fun `should return NOT FOUND if the products don't exist`() {
                // given
                val category = 20

                // when/then
                mockMvc.get("$baseUrl/category/$category")
                    .andDo { print() }
                    .andExpect { status { isNotFound() } }
            }
    }
}