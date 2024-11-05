package com.example.api_spring.api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "relacionamento_usuario")
public class RelacionamentoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relacionamento")
    private Long idRelacionamento;

    @Column(name = "id_usuario_seguidor")
    private Long idUsuarioSeguidor;

    @Column(name = "id_usuario_seguido")
    private Long idUsuarioSeguido;
}
