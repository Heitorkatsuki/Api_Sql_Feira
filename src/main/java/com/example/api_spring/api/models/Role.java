package com.example.api_spring.api.models;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Schema(description = "Entidade que representa os papéis (roles) dos usuários no sistema")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do papel", example = "1")
    private long id;

    @Column(name = "role_name", nullable = false, unique = true)
    @Schema(description = "Nome do papel (role)", example = "ADMIN")
    private String roleName;
}
