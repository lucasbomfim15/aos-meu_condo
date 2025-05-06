package com.aos.meu_condo.modules.accountability.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountabilityDTO {
    private String descricao;
    private Double valor;
    private LocalDate data;
    private Long userId;
    private Long condominiumId;
    // getters e setters
}