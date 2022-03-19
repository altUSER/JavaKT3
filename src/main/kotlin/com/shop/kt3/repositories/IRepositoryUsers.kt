package com.shop.kt3.repositories

import com.shop.kt3.model.CUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface IRepositoryUsers: JpaRepository<CUser, UUID> {
    fun findBySex(sex: Boolean): List<CUser>
}