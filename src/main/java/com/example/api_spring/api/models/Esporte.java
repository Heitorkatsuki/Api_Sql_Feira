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
@Table(name = "esporte")
@Schema(description = "Entidade que representa um esporte")
public class Esporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_esporte")
    @Schema(description = "ID único do esporte", example = "1")
    private Long idEsporte;

    @NotNull
    @Size(max = 40, message = "O nome do esporte não pode ter mais do que 40 caracteres")
    @Schema(description = "Nome do esporte", maxLength = 40, example = "Futebol")
    private String nome;

    @Size(max = 300, message = "A descrição não pode ter mais do que 300 caracteres")
    @Schema(description = "Descrição do esporte", maxLength = 300, example = "Esporte coletivo jogado com uma bola em um campo.")
    private String descricao;

    @NotNull
    @Column(name = "como_pratica")
    @Schema(description = "Descrição de como o esporte é praticado", example = "Jogado em um campo com duas equipes de 11 jogadores.")
    private String comoPratica;

    @NotNull
    @Schema(description = "URL da imagem representativa do esporte", example = "https://exemplo.com/imagem_futebol.jpg")
    private String imagem;
}
