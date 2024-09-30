package com.example.api_spring.models;

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
@Table(name = "centro_esportivo")
public class CentroEsportivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centro")
    private Long idCentro;

    @NotNull
    @Size(max = 40,message = "")
    private String nome;

    @Size(max = 20)
    private String telefone;

    @Size(max = 300)
    private String descricao;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_local")
    private Local idLocal;
}
