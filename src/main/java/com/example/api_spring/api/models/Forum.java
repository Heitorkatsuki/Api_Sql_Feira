package com.example.api_spring.api.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forum")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forum")
    private Long idForum;

    @NotNull
    @Size(max = 40, message = "O nome do fórum deve conter no maximo 40 caracteres")
    private String nome;

    @Size(max = 300, message = "A descrição deve conter no maximo 300 caracteres")
    private String descricao;

    @Column(name = "img_fundo")
    @Size(max = 500)
    private String imgFundo;

    @Column(name = "usuario_resp")
    private Long usuarioResp;

    @Column(name = "img_forum")
    private String imgForum;

    private int seguidores;
}
