package com.example.productservice.service

import com.example.productservice.model.Category
import com.example.productservice.model.Product
import com.example.productservice.repo.CategoryRepo
import com.example.productservice.repo.ProductRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(private val productRepo: ProductRepo) {
    //post product
    fun saveProduct(product: Product): Product? {
        return productRepo.save(product)
    }

    //get products
    val products: List<Product>
        get() = productRepo.findAll()

    //get product by id
    fun getProductById(id: Int): Optional<Product> = productRepo.findById(id)

    //delete product
    fun deleteProduct(id: Int): String {
        productRepo.deleteById(id)
            return "Product deleted $id"
    }

    //update user
    fun updateProduct(product: Product): Product {
        val existingProduct = productRepo.findById(product.id!!).orElse(null)
        existingProduct.name = product.name
        existingProduct.description = product.description
        existingProduct.price = product.price
        return productRepo.save(existingProduct)
    }

    fun findByCategoryId(id: Int): List<Product> = productRepo.findByCategoryId(id)
}