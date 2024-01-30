package com.fiap.TechChallenge4.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
    private UUID id;
    @NotBlank(message = "nome não pode ser vazio")
    private String nome;
    @NotBlank(message = "email não pode ser vazio")
    private String email;
}
