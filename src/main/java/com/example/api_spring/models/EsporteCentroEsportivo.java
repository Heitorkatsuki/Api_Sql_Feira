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
    private Long idEsporteCentro;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_esporte", referencedColumnName = "id_esporte")
    private Esporte idEsporte;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_centro", referencedColumnName = "id_centro")
    private CentroEsportivo idCentro;
}
