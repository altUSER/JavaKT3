package com.shop.kt3.model

import com.fasterxml.jackson.annotation.JsonIdentityReference
import java.util.*
import javax.persistence.*

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
        val category : String = "",

        @OneToMany(mappedBy = "good", fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE], orphanRemoval = true)
        @JsonIdentityReference(alwaysAsId = true)
        var orders: MutableList<COrder> = ArrayList()
) {
}