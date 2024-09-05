package com.example.api_spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "")
public class Anuncio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anuncio")
    private int idAnuncio;

    private double preco;

    @Size(max = 40, message = "")
    private String nome;

    private int quant;

    //    id_usuario INT REFERENCES Usuario(id_usuario),
    private Usuario usuarioId;

    //    id_estado INT REFERENCES Estado(id_estado)
    private Estado estadoId;

}
