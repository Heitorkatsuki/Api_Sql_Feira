package com.example.api_spring.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

// TODO: VER PORQUE DA ERRO
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_interesse")
public class UsuarioInteresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_interesse")
    private Long idUsuarioInteresse;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Size(max = 200,message = "")
    private String interesse;

    @Size(max = 300)
    private String bio;
}
