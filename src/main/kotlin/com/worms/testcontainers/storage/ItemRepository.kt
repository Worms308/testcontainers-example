package com.worms.testcontainers.storage

import org.springframework.data.domain.Pageable

internal interface ItemRepository {
    fun findAllItems(pageable: Pageable): List<Item>

    fun insertItem(item: Item): Item
}
