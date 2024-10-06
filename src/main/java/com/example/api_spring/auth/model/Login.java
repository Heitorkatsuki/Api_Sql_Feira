package com.example.api_spring.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "Modelo que representa a entidade de login")
public class Login {
    @NotNull(message = "O apelido não pode ser nulo")
    private String username;

    @NotNull(message = "A senha não pode ser nula")
    @Schema(description = "Senha do usuário")
    private String senha;
}
