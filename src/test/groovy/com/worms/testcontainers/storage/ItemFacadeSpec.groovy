package com.worms.testcontainers.storage

import com.worms.testcontainers.storage.dto.ItemResponse
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

import static java.util.Collections.emptyList

class ItemFacadeSpec extends Specification {

    def itemRepository = Mock(ItemRepository)
    ItemFacade itemFacade = new ItemFacade(itemRepository)

    def "should get no items from empty storage"() {
        given:
            itemRepository.findAllItems(_) >> emptyList()

        when:
            List<ItemResponse> items = itemFacade.findAllItems(PageRequest.of(0,10))

        then:
            items.isEmpty()
    }

}