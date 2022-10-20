package com.worms.testcontainers.storage

import com.worms.testcontainers.storage.dto.ItemPayload
import com.worms.testcontainers.storage.dto.ItemResponse
import org.springframework.data.domain.Pageable

internal class ItemFacade internal constructor(
    private val itemRepository: ItemRepository
) {

    fun findAllItems(page: Pageable): List<ItemResponse> =
        itemRepository.findAllItems(page)
            .map { it.mapToResponse() }

    fun insertItem(itemPayload: ItemPayload): Item =
        itemRepository.insertItem(Item.from(itemPayload))
}