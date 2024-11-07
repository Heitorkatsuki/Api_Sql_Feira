package com.example.api_spring.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estado")
@Schema(description = "Entidade que representa um estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    @Schema(description = "ID único do estado", example = "1")
    public Long estado;

    @Size(max = 40)
    @Schema(description = "Nome do estado", maxLength = 40, example = "São Paulo")
    public String nome;
}
