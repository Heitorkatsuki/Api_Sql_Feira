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
@Table(name = "filtros")
public class Filtro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filtros")
    private Long idFiltros;

    @NotNull
    @Size(max = 30, message = "O campo categoria deve conter no máximo 30 caracteres")
    private String categoria;

    @Column(name = "id_anuncio")
    private Long idAnuncio;
}
