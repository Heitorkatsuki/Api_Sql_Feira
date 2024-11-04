package com.example.api_spring.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_vendedor")
public class Vendedor {
    @Id
    @Schema(description = "o Id do vendedor equivalente ao do mesmo usuário na tabela usuário", example = "123")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @Size(max = 11, message = "Seu cpf deve conter no maximo 11 caracteres")
    private String cpf;

    @NotNull
    @Email
    @Size(max = 255, message = "Seu endereco deve conter no maximo 255 caracteres")
    private String endereco;

    @NotNull
    @Size(max = 8, message = "Seu cep deve conter no máximo 8 caracteres")
    private String cep;

    @NotNull
    @Size(max = 4, message = "Seu numero deve conter no máximo 4 caracteres")
    private int numero;

    @NotNull
    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @NotNull
    @Size(max = 11, message = "Seu numero deve conter no máximo 11 caracteres")
    private String telefone;
}
