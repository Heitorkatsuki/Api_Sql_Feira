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
@Table(name = "centro_esportivo")
@Schema(description = "Entidade que representa um centro esportivo")
public class CentroEsportivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centro")
    @Schema(description = "ID único do centro esportivo", example = "1")
    private Long idCentro;

    @NotNull
    @Size(max = 40)
    @Schema(description = "Nome do centro esportivo", maxLength = 40, example = "Academia Central")
    private String nome;

    @Size(max = 20)
    @Schema(description = "Telefone de contato do centro esportivo", maxLength = 20, example = "(11) 98765-4321")
    private String telefone;

    @Size(max = 300)
    @Schema(description = "Descrição detalhada do centro esportivo", maxLength = 300, example = "Espaço completo para atividades esportivas e recreativas.")
    private String descricao;

    @Column(name = "id_usuario")
    @Schema(description = "ID do usuário local responsável pelo centro esportivo", example = "10")
    private Long idLocal;
}
