package com.example.api_spring.api.models;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forum_postagem")
    private int idForumPostagem;

    @Column(name = "id_forum")
    private Long idForum;

    @Column(name = "id_postagem")
    private Long idPostagem;
}
