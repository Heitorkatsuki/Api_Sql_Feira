package com.example.api_spring.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_interesse")
@Schema(description = "Entidade que representa os interesses de um usuário no sistema")
public class UsuarioInteresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_interesse")
    @Schema(description = "ID único do interesse do usuário", example = "1")
    private Long idUsuarioInteresse;

    @Column(name = "id_usuario")
    @Schema(description = "ID do usuário ao qual o interesse pertence", example = "123")
    private Long idUsuario;

    @Size(max = 200, message = "O interesse deve conter no máximo 200 caracteres")
    @Schema(description = "Interesse específico do usuário", maxLength = 200, example = "Natação")
    private String interesse;

    @Size(max = 300)
    @Schema(description = "Biografia do interesse", maxLength = 300, example = "Olá, gosto de nadar")
    private String bio;
}
