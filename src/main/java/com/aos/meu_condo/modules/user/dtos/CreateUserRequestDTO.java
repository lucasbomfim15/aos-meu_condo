package com.aos.meu_condo.modules.user.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserRequestDTO {

    @NotBlank
    public String username;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String password;

    @NotBlank
    @NotNull
    public String fullName;

    @NotBlank
    public String phoneNumber;

    @NotBlank
    public String cpf;

}
