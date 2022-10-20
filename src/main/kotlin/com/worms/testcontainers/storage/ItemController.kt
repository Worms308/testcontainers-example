package com.worms.testcontainers.storage

import com.worms.testcontainers.storage.dto.ItemPayload
import com.worms.testcontainers.storage.dto.ItemResponse
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/items")
internal class ItemController(
    private val itemFacade: ItemFacade
) {

    @GetMapping()
    fun findAllItems(@RequestParam page: Int, @RequestParam pageSize: Int): List<ItemResponse> {
        val pageRequest = PageRequest.of(page, pageSize)
        return itemFacade.findAllItems(pageRequest)
    }

    @PostMapping()
    fun insertItem(@RequestBody itemPayload: ItemPayload) {
        itemFacade.insertItem(itemPayload)
    }
}