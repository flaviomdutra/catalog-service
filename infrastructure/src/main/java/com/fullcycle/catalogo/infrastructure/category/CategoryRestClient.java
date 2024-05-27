package com.fullcycle.catalogo.infrastructure.category;

import com.fullcycle.catalogo.domain.category.Category;
import com.fullcycle.catalogo.infrastructure.category.models.CategoryDTO;
import com.fullcycle.catalogo.infrastructure.configuration.annotations.Categories;
import com.fullcycle.catalogo.infrastructure.utils.HttpClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;
import java.util.Optional;

@Component
public class CategoryRestClient implements CategoryClient, HttpClient {

    public static final String NAMESPACE = "categories";

    private final RestClient restClient;

    public CategoryRestClient(@Categories final RestClient categoryHttpClient) {
        this.restClient = Objects.requireNonNull(categoryHttpClient);
    }

    @Override
    public String namespace() {
        return NAMESPACE;
    }

    public Optional<Category> categoryOfId(String categoryId) {
        return doGet(categoryId, () ->
            this.restClient.get()
                .uri("/{id}", categoryId)
                .retrieve()
                .onStatus(isNotFound, notFoundHandler(categoryId))
                .onStatus(is5xx, a5xxHandler(categoryId))
                .body(CategoryDTO.class)
        )
            .map(CategoryDTO::toCategory);
    }
}