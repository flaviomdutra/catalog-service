package com.fullcycle.catalogo.infrastructure.category;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcycle.catalogo.IntegrationTestConfiguration;
import com.fullcycle.catalogo.domain.Fixture;
import com.fullcycle.catalogo.infrastructure.category.models.CategoryDTO;
import com.fullcycle.catalogo.infrastructure.configuration.WebServerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ActiveProfiles("test-integration")
@AutoConfigureWireMock(port = 0)
@EnableAutoConfiguration(
    exclude = {
        ElasticsearchRepositoriesAutoConfiguration.class,
        KafkaAutoConfiguration.class
    }
)
@SpringBootTest(classes = {
    WebServerConfig.class,
    IntegrationTestConfiguration.class
})
public class CategoryRestClientTest {

    @Autowired
    private ObjectMapper objectMapper;

    // OK
    @Test
    public void givenACategory_whenReceive200FromServer_shouldBeOk() throws IOException {

        // given
        final var aulas = Fixture.Categories.aulas();

        final var responseBody = objectMapper.writeValueAsString(
            new CategoryDTO(
                aulas.id(),
                aulas.name(),
                aulas.description(),
                aulas.active(),
                aulas.createdAt(),
                aulas.updatedAt(),
                aulas.deletedAt()
            )
        );

        stubFor(
            get(urlPathEqualTo("/api/categories/1"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(responseBody)
                )
        );
    }
}
