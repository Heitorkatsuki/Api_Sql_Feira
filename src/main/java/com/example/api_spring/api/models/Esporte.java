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
@Table(name = "esporte")
public class Esporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_esporte")
    private Long idEsporte;

    @NotNull
    @Size(max = 40, message = "O nome do esperte não pode ter mais do que 40 caracteres")
    private String nome;

    @Size(max = 300, message = "A descricção não pode ter mais do que 300 caracteres")
    private String descricao;

    @NotNull
    private String comoPratica;
}
