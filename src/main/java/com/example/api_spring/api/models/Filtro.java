package com.example.api_spring.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filtros")
@Schema(description = "Entidade que representa um filtro de anúncio")
public class Filtro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filtros")
    @Schema(description = "ID único do filtro", example = "1")
    private Long idFiltros;

    @NotNull
    @Size(max = 30, message = "O campo categoria deve conter no máximo 30 caracteres")
    @Schema(description = "Categoria do filtro", maxLength = 30, example = "Futebol")
    private String categoria;

    @Column(name = "id_anuncio")
    @Schema(description = "ID do anúncio associado ao filtro", example = "12")
    private Long idAnuncio;
}
