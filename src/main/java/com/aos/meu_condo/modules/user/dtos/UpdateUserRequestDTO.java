package com.aos.meu_condo.modules.user.dtos;

import lombok.Data;

@Data
public class UpdateUserRequestDTO {
    private String fullName;
    private String phoneNumber;
    private String cpf;
}