package com.worms.testcontainers.storage

import com.worms.testcontainers.storage.dto.ItemPayload
import com.worms.testcontainers.storage.dto.ItemResponse
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.*

@Document("items")
internal data class Item(
    @Id
    val id: UUID,
    val mass: Double,
    val price: BigDecimal
) {
    fun mapToResponse(): ItemResponse = ItemResponse(
        id, mass, price
    )

    companion object {
        fun from(itemPayload: ItemPayload) = Item(
            UUID.randomUUID(), itemPayload.mass, itemPayload.price
        )
    }

}
