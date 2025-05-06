package com.aos.meu_condo.modules.apartment.dtos;

import lombok.Data;

@Data
public class ApartmentDTO {
    private int floor;
    private int number;
    private Long userId;
    private Long condominiumId;
}
