package com.example.api_spring.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

    // TODO: Colocar o @generated para o campo se autocompletar, o @ deve ser colocado logo apos o @ id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id único do usuário", example = "123")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @Size(max = 40, message = "Seu nome deve conter no maximo 40 caracteres")
    private String nome;

    @NotNull
    @Email
    @Size(max = 60, message = "Seu email deve conter no maximo 60 caracteres")
    private String email;

    @NotNull
    // TODO: Constraint
    @Size(max = 20, message = "Sua senha deve contar no máximo 20 caracteres")
    private String senha;

    @NotNull
    // TODO: Constraint
    @Column(name = "dt_nasc")
    private Date dtNasc;

    @NotNull
    @Size(max = 25, message = "Seu nome de usuário deve contar no máximo 25 caracteres")
    private String username;

    @Column(name = "foto_perfil")
    private String fotoPerfil;
}
