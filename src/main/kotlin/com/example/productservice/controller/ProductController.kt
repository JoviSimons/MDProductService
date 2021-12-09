package com.example.productservice.controller

import com.example.productservice.exceptions.NotFoundException
import com.example.productservice.model.Product
import com.example.productservice.repo.ProductRepo
import com.example.productservice.service.ProductService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/MyDrugs")
@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
internal class ProductController(productService: ProductService) {
    private val service: ProductService


    @GetMapping("/products")
    fun all(): List<Product> = service.products


    @GetMapping("/products/category/{id}")
    fun getByCategoryId(@PathVariable id: Int): List<Product>{
        if(service.findByCategoryId(id).isNotEmpty())
            return service.findByCategoryId(id)
        throw NotFoundException("Products not found")
    }

    // end::get-aggregate-root[]
    @PostMapping("/products")
    fun newProduct(@RequestBody newProduct: Product): Product? {
        return service.saveProduct(newProduct)
    }

    // Single item
    @GetMapping("/products/{id}")
    fun one(@PathVariable id: Int): Optional<Product> {
        return service.getProductById(id)
    }

    @PutMapping("/products/{id}")
    fun replaceProduct(@RequestBody newProduct: Product, @PathVariable id: Int): Product {
        return service.updateProduct(newProduct)
    }

    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable id: Int) {
        service.deleteProduct(id)
    }

    init {
        this.service = productService
    }

}
