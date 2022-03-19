package com.shop.kt3.rest

import com.shop.kt3.model.CGood
import com.shop.kt3.repositories.IRepositoryGoods
import jdk.jfr.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/goods")
class CControllerGoods {
    @Autowired
    private lateinit var repositoryGoods: IRepositoryGoods

    @GetMapping("")
    fun getAll(): List<CGood>{
        return repositoryGoods.findAll()
    }

    @GetMapping(params=["id"])
    fun getById(@RequestParam id: UUID): CGood? {
        return repositoryGoods.findByIdOrNull(id)
    }

    @GetMapping(params = ["category"])
    fun getByCategory(@RequestParam category: String): List<CGood>{
        return repositoryGoods.findByCategory(category)
    }

    @GetMapping(params = ["name"])
    fun getByName(@RequestParam name: String): List<CGood>{
        return repositoryGoods.findByName(name)
    }


    @PostMapping
    fun saveGood(@RequestBody good: CGood){
        repositoryGoods.saveAndFlush(good)
    }


    @DeleteMapping
    fun deleteGood(@RequestBody good: CGood){
        repositoryGoods.delete(good)
    }
}