package com.shop.kt3.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name="orders")
class COrder (

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name="id", updatable = false, nullable = false)
    val id: UUID? = null,

    @ManyToOne
    @JoinColumn(name="owner", nullable = false)
    var owner: CUser,

    @Column
    var date : Date? = Date(),

    @ManyToOne
    @JoinColumn(name="good", nullable = false)
    var good: CGood,
)