package com.aos.meu_condo.modules.user.mappers;

import com.aos.meu_condo.modules.user.dtos.CreateUserRequestDTO;
import com.aos.meu_condo.modules.user.dtos.CreateUserResponseDTO;
import com.aos.meu_condo.modules.user.dtos.UpdateUserRequestDTO;
import com.aos.meu_condo.modules.user.model.User;

public class UserMapper {

    public static User toEntity(CreateUserRequestDTO dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .cpf(dto.getCpf())
                .build();
    }

    public static CreateUserResponseDTO toResponse(User user) {
        CreateUserResponseDTO response = new CreateUserResponseDTO();
        response.id = user.getUserId();
        response.username = user.getUsername();
        response.email = user.getEmail();
        response.fullName = user.getFullName();
        response.phoneNumber = user.getPhoneNumber();
        response.cpf = user.getCpf();
        return response;
    }


    public static void updateEntityFromDto(User user, UpdateUserRequestDTO dto) {
        user.setFullName(dto.getFullName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setCpf(dto.getCpf());
    }
}
