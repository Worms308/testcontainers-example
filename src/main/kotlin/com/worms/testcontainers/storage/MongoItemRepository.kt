package com.worms.testcontainers.storage

import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
internal class MongoItemRepository(
    private val mongoTemplate: MongoTemplate
) : ItemRepository {

    override fun findAllItems(pageable: Pageable): List<Item> {
        val query = Query().with(pageable)
        return mongoTemplate.find(query, Item::class.java)
    }

    override fun insertItem(item: Item): Item {
        return mongoTemplate.insert(item)
    }
}