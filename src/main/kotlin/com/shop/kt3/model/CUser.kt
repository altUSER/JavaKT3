package com.shop.kt3.model

import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name="users")
class CUser(
        @Id
        @Column(name="id", updatable = false, nullable = false)
        val id: UUID? = null,

        @Column(name = "login")
        var login: String = "",

        @Column(name = "name")
        var name: String = "",

        @Column(name = "sex")
        var sex: Boolean = false,

        @Column(name = "dateOfBirth", columnDefinition = "DATE")
        var dayOfBirth: LocalDate? = null
)
