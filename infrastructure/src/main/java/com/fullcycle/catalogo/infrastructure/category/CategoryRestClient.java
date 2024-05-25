package com.fullcycle.catalogo.infrastructure.category;

import com.fullcycle.catalogo.domain.category.Category;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryRestClient implements CategoryClient {

    @Override
    public Optional<Category> categoryOfId(String anId) {
        return Optional.empty();
    }
}
