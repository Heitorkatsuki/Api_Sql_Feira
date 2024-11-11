package com.example.api_spring.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "evento")
@Schema(description = "Entidade que representa um evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    @Schema(description = "ID único do evento", example = "1")
    private Long idEvento;

    @NotNull
    @Size(max = 40)
    @Schema(description = "Nome do evento", maxLength = 40, example = "Copa de Futebol")
    private String nome;

    @Size(max = 2000)
    @Schema(description = "Descrição do evento", maxLength = 300, example = "Evento esportivo para celebrar o futebol local.")
    private String descricao;

    @Column(name = "dt_evento")
    @Schema(description = "Data do evento", example = "2024-12-31T18:00:00")
    private Date dtEvento;

    @Size(max = 200)
    @Schema(description = "Imagem do evento guardada no firebase", maxLength = 200, example = "evento/imagem_evento.jpg")
    private String img;

    @Column(name = "organizador")
    @Schema(description = "ID do organizador do evento", example = "5")
    private Long organizador;

    @Column(name = "id_local")
    @Schema(description = "ID do local do evento", example = "10")
    private Long idLocal;
}
