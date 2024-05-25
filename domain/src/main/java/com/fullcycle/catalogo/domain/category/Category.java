package com.fullcycle.catalogo.domain.category;

import com.fullcycle.catalogo.domain.validation.Error;
import com.fullcycle.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category {
    private final String id;
    private final String name;
    private final String description;
    private final boolean active;
    private final Instant createdAt;
    private final Instant updatedAt;
    private final Instant deletedAt;

    private Category(
        final String anId,
        final String aName,
        final String aDescription,
        final boolean isActive,
        final Instant aCreationDate,
        final Instant aUpdateDate,
        final Instant aDeleteDate
    ) {
        this.id = anId;
        this.name = aName;
        this.description = aDescription;
        this.active = isActive;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
        this.deletedAt = aDeleteDate;
    }

    public static Category with(
        final String anId,
        final String aName,
        final String aDescription,
        final boolean isActive,
        final Instant aCreationDate,
        final Instant aUpdateDate,
        final Instant aDeleteDate
    ) {
        return new Category(
            anId,
            aName,
            aDescription,
            isActive,
            aCreationDate,
            aUpdateDate,
            aDeleteDate
        );
    }

    public static Category with(final Category category) {
        return with(
            category.id(),
            category.name(),
            category.description(),
            category.active(),
            category.createdAt(),
            category.updatedAt(),
            category.deletedAt()
        );
    }

    public Category validate(final ValidationHandler aHandler) {
        if (id == null || id.isBlank()) {
            aHandler.append(new Error("'id' should not be empty"));
        }

        if (name == null || name.isBlank()) {
            aHandler.append(new Error("'name' should not be empty"));
        }

        return this;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public boolean active() {
        return active;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public Instant deletedAt() {
        return deletedAt;
    }
}
