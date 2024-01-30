package com.fiap.TechChallenge4.Model;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Video {

    private String id;
    private String fileName;
    @NotBlank(message = "titulo não pode ser vazio")
    private String titulo;
    @NotBlank(message = "descricao não pode ser vazia")
    private String descricao;
    private String url;
    private LocalDateTime dataPublicacao;
    private String tags;

}
