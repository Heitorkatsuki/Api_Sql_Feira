package com.example.api_spring.api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "esporte_centro_esportivo")
public class EsporteCentroEsportivo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_esporte_centro")
    private Long idEsporteCentro;

    @Column(name = "id_esporte")
    private Long idEsporte;


    @Column(name = "id_centro")
    private Long idCentro;
}
