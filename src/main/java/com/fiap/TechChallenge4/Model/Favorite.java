package com.fiap.TechChallenge4.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Favorite {
    @Id
    String id;
    String userId;
    String videoId;
}
