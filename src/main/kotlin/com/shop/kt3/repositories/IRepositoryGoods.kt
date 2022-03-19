package com.shop.kt3.repositories

import com.shop.kt3.model.CGood
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IRepositoryGoods: JpaRepository<CGood, UUID> {
    fun findByCategory(category: String): List<CGood>
    fun findByName(name: String): List<CGood>
}