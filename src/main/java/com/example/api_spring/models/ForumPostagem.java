package com.example.api_spring.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forum_postagem")
public class ForumPostagem {
    // TODO: VER SE É INTEGER OU LONG
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forum_postagem")
    private int idForumPostagem;

    // TODO: VER O @ DO RELACIONAMENTO
    @Column(name = "id_forum")
    @JoinColumn(name = "id_forum", referencedColumnName = "id_forum")
    private Forum idForum;

    @Column(name = "id_postagem")
    @JoinColumn(name = "id_")
    private Postagem idPostagem;
}
