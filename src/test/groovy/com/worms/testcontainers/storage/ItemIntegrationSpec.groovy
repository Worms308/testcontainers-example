package com.worms.testcontainers.storage

import com.worms.testcontainers.storage.dto.ItemPayload
import org.springframework.http.HttpHeaders

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ItemIntegrationSpec extends IntegrationSpec {


    def "should insert and get item from DB"() {
        given:
            def itemPayload = new ItemPayload(123.432, 6.9g)

        when:
            def insertResult = mockMvc.perform(post("/items")
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .content(objectMapper.writeValueAsString(itemPayload))
            )

        then:
            insertResult.andExpect(status().isOk())

        when:
            def result = mockMvc.perform(get("/items")
                    .param("page", "0")
                    .param("pageSize", "10")
            )

        then:
            result.andExpect(status().isOk())

    }
}