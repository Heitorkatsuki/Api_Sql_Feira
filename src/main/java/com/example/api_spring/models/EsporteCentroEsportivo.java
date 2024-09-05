package com.example.api_spring.models;

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
    private int idEsporteCentro;

    // TODO: RELACIONAMENTO
    @ManyToOne
    @JoinColumn(name = "id_esporte")
    @Column(name = "id_esporte")
    private Esporte idEsporte;

    // TODO: RELACIONAMENTO
    @ManyToOne
    @JoinColumn(name = "id_centro")
    @Column(name = "id_centro")
    private CentroEsportivo idCentro;
}