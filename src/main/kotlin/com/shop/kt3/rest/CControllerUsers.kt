package com.shop.kt3.rest

import com.shop.kt3.model.CUser
import com.shop.kt3.repositories.IRepositoryUsers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/users")
class CControllerUsers {
    @Autowired
    private lateinit var repositoryUsers: IRepositoryUsers

    @GetMapping("")
    fun getAll(): List<CUser>{
        return repositoryUsers.findAll()
    }

    @GetMapping(params=["id"])
    fun getById(@RequestParam id: UUID): CUser? {
        return repositoryUsers.findByIdOrNull(id)
    }
}