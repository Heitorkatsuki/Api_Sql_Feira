package com.example.api_spring.api.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseAthleta {
    private boolean responseSucessfull;
    private String description;
    private List<Object> object;
    private String aditionalInformation;
}
