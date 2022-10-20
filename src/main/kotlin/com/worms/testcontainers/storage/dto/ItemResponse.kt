package com.worms.testcontainers.storage.dto

import java.math.BigDecimal
import java.util.*

data class ItemResponse(
    val id: UUID,
    val mass: Double,
    val price: BigDecimal
)
