package com.example.api_spring.api.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean responseSucessfull;
    private String description;
    private List<Object> object;
    private String aditionalInformation;
}
