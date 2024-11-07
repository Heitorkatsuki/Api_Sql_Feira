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
@Table(name = "relacionamento_usuario")
@Schema(description = "Entidade que representa o relacionamento entre usuários (seguidor e seguido)")
public class RelacionamentoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relacionamento")
    @Schema(description = "ID único do relacionamento", example = "1")
    private Long idRelacionamento;

    @Column(name = "id_usuario_seguidor")
    @Schema(description = "ID do usuário seguidor", example = "2")
    private Long idUsuarioSeguidor;

    @Column(name = "id_usuario_seguido")
    @Schema(description = "ID do usuário seguido", example = "5")
    private Long idUsuarioSeguido;
}
