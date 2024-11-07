package com.example.api_spring.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
@Schema(description = "Entidade que representa os usuários no sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário", example = "123")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Size(max = 40, message = "Seu nome deve conter no máximo 40 caracteres")
    @Schema(description = "Nome completo do usuário", maxLength = 40, example = "João Silva")
    private String nome;

    @NotNull
    @Email
    @Size(max = 60, message = "Seu email deve conter no máximo 60 caracteres")
    @Schema(description = "Email do usuário", maxLength = 60, example = "joao.silva@gmail.com")
    private String email;

    @NotNull
    @Size(max = 300, message = "Sua senha deve conter no máximo 300 caracteres")
    @Schema(description = "Senha do usuário", maxLength = 300, example = "senha123")
    private String senha;

    @NotNull
    @Past(message = "A data de nascimento deve ser uma data passada")
    @Schema(description = "Data de nascimento do usuário", example = "1990-05-20")
    @Column(name = "dt_nasc")
    private Date dtNasc;

    @NotNull
    @Size(max = 25, message = "Seu nome de usuário deve conter no máximo 25 caracteres")
    @Schema(description = "Nome de usuário para login", maxLength = 25, example = "joaosilva")
    private String username;

    @Column(name = "foto_perfil")
    @Schema(description = "Foto de perfil do usuário", example = "path/to/photo.jpg")
    private String fotoPerfil;

    @Column(name = "user_role")
    @Schema(description = "ID do papel (role) associado ao usuário", example = "1")
    private Long userRole;
}
