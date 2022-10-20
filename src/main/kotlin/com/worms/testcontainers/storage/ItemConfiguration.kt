package com.worms.testcontainers.storage

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class ItemConfiguration {

    @Bean
    fun itemFacade(itemRepository: ItemRepository): ItemFacade {
        return ItemFacade(itemRepository)
    }
}