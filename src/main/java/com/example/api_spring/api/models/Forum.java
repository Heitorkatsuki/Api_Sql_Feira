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
@Table(name = "forum")
@Schema(description = "Entidade que representa um fórum")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forum")
    @Schema(description = "ID único do fórum", example = "1")
    private Long idForum;

    @NotNull
    @Size(max = 40, message = "O nome do fórum deve conter no máximo 40 caracteres")
    @Schema(description = "Nome do fórum", maxLength = 40, example = "Vôlei masters")
    private String nome;

    @Size(max = 300, message = "A descrição deve conter no máximo 300 caracteres")
    @Schema(description = "Descrição do fórum", maxLength = 300, example = "Fórum para discutir sobre jogos de vôlei")
    private String descricao;

    @Column(name = "img_fundo")
    @Size(max = 500)
    @Schema(description = "Caminho da imagem de fundo do fórum no firebase", maxLength = 500, example = "forum/img_fundo_forum.jpg")
    private String imgFundo;

    @Column(name = "usuario_resp")
    @Schema(description = "ID do usuário responsável pelo fórum", example = "2")
    private Long usuarioResp;

    @Column(name = "img_forum")
    @Schema(description = "Caminho da imagem do fórum no firebase", example = "forum/img_forum.jpg")
    private String imgForum;

    @Schema(description = "Número de seguidores do fórum", example = "150")
    private int seguidores;
}
