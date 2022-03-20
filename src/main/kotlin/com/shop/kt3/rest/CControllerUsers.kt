package com.shop.kt3.rest

import com.shop.kt3.model.COrder
import com.shop.kt3.model.CUser
import com.shop.kt3.repositories.IRepositoryUsers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/users")
class CControllerUsers {
    @Autowired
    lateinit var repositoryUsers: IRepositoryUsers

    @GetMapping("")
    fun getAll(): List<CUser>{
        return repositoryUsers.findAll()
    }

    @GetMapping(params=["id"])
    fun getById(@RequestParam id: UUID): CUser? {
        return repositoryUsers.findByIdOrNull(id)
    }

    @GetMapping(params=["sex"])
    fun getBySex(@RequestParam sex: Boolean): List<CUser>{
        return repositoryUsers.findBySex(sex)
    }

    @PostMapping
    fun saveUsers(@RequestBody user: CUser){
        repositoryUsers.saveAndFlush(user)
    }

    @DeleteMapping
    fun deketeUser(@RequestBody user: CUser){
        repositoryUsers.delete(user)
    }
}