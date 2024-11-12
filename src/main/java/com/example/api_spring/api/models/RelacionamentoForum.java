package com.example.api_spring.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "relacionamento_forum")
public class RelacionamentoForum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relacionamento")
    @Schema(description = "ID único do relacionamento", example = "1")
    private Long idRelacionamento;

    @Column(name = "id_forum")
    private Long idForum;

    @Column(name = "id_usuario")
    private Long idUsuario;
}
