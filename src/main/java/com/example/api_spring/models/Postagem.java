package com.example.api_spring.postgresql.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "postagem")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postagem")
    private Long idPostagem;

    @Size(max = 200, message = "O texto da postagem deve conter no maximo 200 caracteres")
    private String texto;

    @Column(name = "dt_postagem")
    private Date dtPostagem;

    private String imagem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario idUsuario;
}
