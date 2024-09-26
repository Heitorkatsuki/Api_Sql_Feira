package com.example.api_spring.postgresql.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "local")
public class Local {
//    id_local serial PRIMARY KEY,
//    num INT,
//    rua VARCHAR(100) NOT NULL,
//    cidade VARCHAR(50) NOT NULL,
//    coordenada VARCHAR(30),
//    cep INT,
//    id_estado INT REFERENCES Estado(id_estado)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local")
    private Long idLocal;

    private int num;

    @NotNull
    @Size(max = 100, message = "")
    private String rua;

    @NotNull
    @Size(max = 50, message = "")
    private String cidade;

    @Size(max = 30, message = "")
    private String coordenada;

    //TODO: PERGUNTAR PQ O CEP N É STRING
    private Long cep;

    // private Estado idEstado

}
