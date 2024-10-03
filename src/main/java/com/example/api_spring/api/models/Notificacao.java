package com.example.api_spring.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacao")
    private Long idNotificacao;

    @Size(max = 20, message = "O tipo deve conter no maximo 20 caracteres")
    private String tipo;

    @Column(name = "dt_notificacao")
    private Date dtNotificacao;

    @Size(max = 200, message = "O conteúdo deve conter no maximo 200 caracteres")
    private String conteudo;

    // TODO: Colocar relacionamento
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuarioId;
}
