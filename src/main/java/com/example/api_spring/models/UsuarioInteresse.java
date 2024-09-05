package com.example.api_spring.models;

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
    // TODO: RELACIONAMENTO
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @Size(max = 200,message = "")
    private String interesse;

    @Size(max = 300)
    private String bio;
}
