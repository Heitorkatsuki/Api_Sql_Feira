package com.example.api_spring.api.models;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "esporte_centro_esportivo")
@Schema(description = "Entidade que representa a associação entre esporte e centro esportivo")
public class EsporteCentroEsportivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_esporte_centro")
    @Schema(description = "ID único da associação entre esporte e centro esportivo", example = "1")
    private Long idEsporteCentro;

    @Column(name = "id_esporte")
    @Schema(description = "ID do esporte associado", example = "5")
    private Long idEsporte;

    @Column(name = "id_centro")
    @Schema(description = "ID do centro esportivo associado", example = "3")
    private Long idCentro;
}
