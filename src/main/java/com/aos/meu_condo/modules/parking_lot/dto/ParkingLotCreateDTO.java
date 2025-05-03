package com.aos.meu_condo.modules.parking_lot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingLotCreateDTO {

    @NotBlank(message = "Spot number cannot be blank.")
    @Size(max = 10, message = "Spot number must be up to 10 characters long.")
    private String spotNumber;

    @Size(max = 50, message = "Block must be up to 50 characters long.")
    private String block;

    @NotNull(message = "Occupied status must be provided (true or false).")
    private Boolean occupied;

    @NotBlank(message = "Spot type cannot be blank.")
    @Size(max = 50, message = "Spot type must be up to 50 characters long.")
    private String spotType;

    @NotNull(message = "Condominium ID cannot be null.")
    private Long condominiumId;
}
