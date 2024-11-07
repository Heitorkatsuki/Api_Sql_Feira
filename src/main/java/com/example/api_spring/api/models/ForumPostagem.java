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
@Table(name = "forum_postagem")
@Schema(description = "Entidade que representa a associação entre fórum e postagem")
public class ForumPostagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forum_postagem")
    @Schema(description = "ID único da associação entre fórum e postagem", example = "1")
    private int idForumPostagem;

    @Column(name = "id_forum")
    @Schema(description = "ID do fórum associado", example = "5")
    private Long idForum;

    @Column(name = "id_postagem")
    @Schema(description = "ID da postagem associada", example = "12")
    private Long idPostagem;
}
