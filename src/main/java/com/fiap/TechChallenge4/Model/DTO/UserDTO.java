package com.fiap.TechChallenge4.Model.DTO;

import com.fiap.TechChallenge4.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String nome;
    private String email;

    public static User DTOtoModel(UserDTO dto) {
        return new User(dto.id != null ? UUID.fromString(dto.id) : null,
                        dto.nome,
                        dto.email);
    }

    public static UserDTO ModeltoDTO(User user) {
        return new UserDTO(user.getId().toString(),
                           user.getNome(),
                           user.getEmail());
    }
}
