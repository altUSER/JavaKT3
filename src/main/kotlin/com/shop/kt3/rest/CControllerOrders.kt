package com.shop.kt3.rest

import com.shop.kt3.model.COrder
import com.shop.kt3.repositories.IRepositoryOrders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/orders")
class CControllerOrders {
    @Autowired
    private lateinit var repositoryOrders: IRepositoryOrders

    @GetMapping("")
    fun getAll(): List<COrder>{
        return repositoryOrders.findAll()
    }

    @GetMapping(params=["id"])
    fun getById(@RequestParam id: UUID): COrder? {
        return repositoryOrders.findByIdOrNull(id)
    }

    @PostMapping
    fun saveOrder(@RequestBody order: COrder){
        if (order.id == null) {
            order.id = UUID.randomUUID()
        }
        repositoryOrders.saveAndFlush(order)
    }
}