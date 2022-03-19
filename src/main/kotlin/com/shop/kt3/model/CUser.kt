package com.shop.kt3.model

import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.web.bind.annotation.PostMapping
import java.time.LocalDate
import java.util.*
import javax.persistence.*


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
        var dateOfBirth: LocalDate? = null,

        @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE], orphanRemoval = true)
        @JsonIdentityReference(alwaysAsId = true)
        var orders: MutableList<COrder> = ArrayList()



)
