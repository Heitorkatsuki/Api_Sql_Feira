package com.example.api_spring.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notificacao")
@Schema(description = "Entidade que representa uma notificação")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacao")
    @Schema(description = "ID único da notificação", example = "1")
    private Long idNotificacao;

    @Size(max = 20, message = "O tipo deve conter no máximo 20 caracteres")
    @Schema(description = "Tipo da notificação", maxLength = 20, example = "Alerta")
    private String tipo;

    @Column(name = "dt_notificacao")
    @Schema(description = "Data e hora em que a notificação foi gerada", example = "2024-11-06T15:30:00")
    private Date dtNotificacao;

    @Size(max = 200, message = "O conteúdo deve conter no máximo 200 caracteres")
    @Schema(description = "Conteúdo da notificação", maxLength = 200, example = "Você tem um novo seguidor!")
    private String conteudo;

    @Column(name = "id_usuario")
    @Schema(description = "ID do usuário relacionado à notificação", example = "10")
    private Long idUsuario;
}
