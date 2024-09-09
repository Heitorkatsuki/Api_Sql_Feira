package com.example.api_spring.models;

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
    private int idForum;

    @NotNull
    @Size(max = 40, message = "O nome do fórum deve conter no maximo 40 caracteres")
    private String nome;

    @Size(max = 300, message = "A descrição deve conter no maximo 300 caracteres")
    private String descricao;

    @Column(name = "img_fundo")
    // TODO: Ver se precisa da mensagem @Size(max = 200,message = "A imagem de fundo deve conter no maximo 200 caracteres")
    private String imgFundo;

    // TODO: Ver se o usuario linka aqui (provavelmente sim)
    private int usuarioResp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario idUsuario;

    // TODO: Ver porque o campo img não está no script

    @Size(max = 200)
    private String img;
}
