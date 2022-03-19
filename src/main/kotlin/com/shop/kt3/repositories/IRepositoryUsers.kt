package com.shop.kt3.repositories

import com.shop.kt3.model.CUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IRepositoryUsers: JpaRepository<CUser, UUID> {

}