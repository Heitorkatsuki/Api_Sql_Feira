package com.example.api_spring.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_vendedor")
@Schema(description = "Entidade que representa um vendedor no sistema")
public class Vendedor {

    @Id
    @Schema(description = "ID do vendedor, que é equivalente ao ID do mesmo usuário na tabela 'usuario'", example = "123")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @Size(max = 11, message = "O CPF deve conter no máximo 11 caracteres")
    @Schema(description = "CPF do vendedor", maxLength = 11, example = "12345678901")
    private String cpf;

    @NotNull
    @Size(max = 255, message = "O endereço deve conter no máximo 255 caracteres")
    @Schema(description = "Endereço do vendedor", maxLength = 255, example = "Rua das Flores, 123")
    private String endereco;

    @NotNull
    @Size(max = 9, message = "O CEP deve conter no máximo 8 caracteres")
    @Schema(description = "CEP do vendedor", maxLength = 9, example = "12345-678")
    private String cep;

    @NotNull
    @Min(1)
    @Max(9999)
    @Schema(description = "Número do endereço do vendedor", example = "1234")
    private int numero;

    @NotNull
    @Column(name = "foto_perfil")
    @Schema(description = "Foto de perfil do vendedor", example = "url_imagem.jpg")
    private String fotoPerfil;

    @NotNull
    @Size(max = 11, message = "O número de telefone deve conter no máximo 11 caracteres")
    @Schema(description = "Telefone do vendedor", maxLength = 11, example = "11987654321")
    private String telefone;
}
