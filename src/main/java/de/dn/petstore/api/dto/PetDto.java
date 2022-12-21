package de.dn.petstore.api.dto;

import jakarta.validation.constraints.NotNull;

public record PetDto(@NotNull String name, String tag) {
}
