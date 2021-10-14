package com.example.productservice.controller

import com.example.productservice.model.Product
import com.example.productservice.repo.ProductRepo
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/MyDrugs")
@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
internal class ProductController(repository: ProductRepo) {
    private val repository: ProductRepo


    @GetMapping("/products")
    fun all(): List<Product> {
        return repository.findAll()
    }

    // end::get-aggregate-root[]
    @PostMapping("/products")
    fun newProduct(@RequestBody newProduct: Product): Product {
        return repository.save(newProduct)
    }

    // Single item
    @GetMapping("/products/{id}")
    fun one(@PathVariable id: Int): Optional<Product> {
        return repository.findById(id)
    }

    @PutMapping("/products/{id}")
    fun replaceProduct(@RequestBody newProduct: Product, @PathVariable id: Int): Product {
        return repository.findById(id)
                .map { product ->
                    product.name = newProduct.name
                    product.description = newProduct.description
                    product.price = newProduct.price
                    repository.save(product)
                }
                .orElseGet {
                    repository.save(newProduct)
                }
    }

    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable id: Int) {
        repository.deleteById(id)
    }

    init {
        this.repository = repository
    }

}