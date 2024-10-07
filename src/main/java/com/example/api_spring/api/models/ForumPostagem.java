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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_forum", referencedColumnName = "id_forum")
    private Forum idForum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_postagem", referencedColumnName = "id_postagem")
    private Postagem idPostagem;
}
