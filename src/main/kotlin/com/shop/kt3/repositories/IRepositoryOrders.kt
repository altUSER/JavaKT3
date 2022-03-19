package com.shop.kt3.repositories

import com.shop.kt3.model.COrder
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IRepositoryOrders: JpaRepository<COrder, UUID> {
}