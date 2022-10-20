package com.worms.testcontainers.storage

import com.fasterxml.jackson.databind.ObjectMapper
import com.worms.testcontainers.Testcontainers2Application
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification


@Testcontainers
@SpringBootTest(classes = [Testcontainers2Application, IntegrationSpecConfiguration])
@AutoConfigureMockMvc
class IntegrationSpec extends Specification {

    @Shared
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:5.0"))
            .withExposedPorts(27017)

    @Autowired
    MockMvc mockMvc

    ObjectMapper objectMapper = new ObjectMapper()

    @DynamicPropertySource
    static def registerProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start()
        registry.add("spring.data.mongodb.uri", () ->  mongoDBContainer.replicaSetUrl)
    }

    def cleanup() {
        mongoDBContainer.stop()
    }
}
