package com.aos.meu_condo.modules.parking_lot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingLotResponseDTO {

    private Long id;
    private String spotNumber;
    private String block;
    private boolean occupied;
    private String spotType;
    private Long condominiumId;
}
