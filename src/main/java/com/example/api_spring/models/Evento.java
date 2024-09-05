package com.example.api_spring.models;

import jakarta.persistence.*;
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
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_evento;

    @NotNull
    @Size(max = 40)
    private String nome;

    @Size(max = 300, message = "")
    private String descricao;

    @Column(name = "dt_evento")
    private Date dtEvento;

    @JoinColumn(name = "id_usuario")
    private Usuario organizador;

    @Size(max = 200, message = "")
    private String img;

    @JoinColumn(name = "id_local")
    @Column(name = "id_local")
    private Local idLocal;
}
