package com.example.api_spring.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anuncio")
@Schema(description = "Entidade que representa um anúncio de venda.")
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anuncio")
    @Schema(description = "ID único do anúncio", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idAnuncio;

    @Schema(description = "Preço do anúncio", example = "299.99")
    private double preco;

    @Size(max = 300)
    @Schema(description = "Descrição do anúncio, com no máximo 300 caracteres", example = "Ótimo estado, pouco usado.")
    private String descricao;

    @Size(max = 40, message = "O nome deve conter no máximo 40 caracteres")
    @Schema(description = "Nome do anúncio", example = "Notebook Dell")
    private String nome;

    @Schema(description = "Quantidade disponível no anúncio", example = "5")
    private int quant;

    @Column(name = "id_usuario")
    @Schema(description = "Usuário que criou o anúncio")
    private Long idUsuario;

    @Column(name = "id_estado")
    @Schema(description = "Estado onde o anúncio está disponível")
    private Long idEstado;
}

