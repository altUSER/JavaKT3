package com.shop.kt3.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name="orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
class COrder (

        @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name="id", updatable = false, nullable = false)
    var id: UUID? = null,

        @ManyToOne
    @JoinColumn(name="owner", nullable = false)
    var owner: CUser,

        @Column
    var date : LocalDate? = LocalDate.now(),

        @ManyToOne
    @JoinColumn(name="good", nullable = false)
    var good: CGood,
)