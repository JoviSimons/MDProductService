package com.example.productservice.model

import javax.persistence.*

@Entity
class Category(@Id
               @GeneratedValue(strategy = GenerationType.IDENTITY)
               var id: Int? = null,
               var name: String? = null,
               var description: String? = null){

    @OneToMany
    @JoinColumn(name = "category_id")
    private val products: List<Product>? = null

}