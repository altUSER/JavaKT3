package com.shop.kt3.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="goods")
class CGood(
        @Id
        @Column(name="id", updatable = false, nullable = false)
        val id : UUID? = null,

        @Column(name="name")
        val name : String = "",

        @Column(name="price")
        val price : Double = 0.0,

        @Column(name="category")
        val category : String = ""
)