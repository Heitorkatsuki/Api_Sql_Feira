package com.example.api_spring.api.models;

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
    @Size(min = 8, max = 9)
    private String cep;

    @Column(name = "id_estado")
    private Long idEstado;

}
