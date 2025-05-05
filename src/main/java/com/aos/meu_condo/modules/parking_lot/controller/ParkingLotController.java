package com.aos.meu_condo.modules.parking_lot.controller;

import com.aos.meu_condo.modules.parking_lot.dto.ParkingLotCreateDTO;
import com.aos.meu_condo.modules.parking_lot.dto.ParkingLotResponseDTO;
import com.aos.meu_condo.modules.parking_lot.service.ParkingLotService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity<ParkingLotResponseDTO> createParkingLot(@Valid @RequestBody ParkingLotCreateDTO createDTO) {
        ParkingLotResponseDTO createdLot = parkingLotService.createParkingLot(createDTO);
        return new ResponseEntity<>(createdLot, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParkingLotResponseDTO>> getParkingLots(
            @RequestParam(required = false) Long condominiumId,
            @RequestParam(required = false) Boolean available
    ) {
        List<ParkingLotResponseDTO> lots;

        if (condominiumId != null) {
            if (Boolean.TRUE.equals(available)) {
                lots = parkingLotService.getAvailableParkingLotsByCondominium(condominiumId);
            } else {
                lots = parkingLotService.getParkingLotsByCondominiumId(condominiumId);
            }
        } else {
            lots = parkingLotService.getAllParkingLots();
        }

        return ResponseEntity.ok(lots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLotResponseDTO> getParkingLotById(@PathVariable Long id) {
        ParkingLotResponseDTO lot = parkingLotService.getParkingLotById(id);
        return ResponseEntity.ok(lot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLotResponseDTO> updateParkingLot(
            @PathVariable Long id,
            @Valid @RequestBody ParkingLotCreateDTO updateDTO) {
        ParkingLotResponseDTO updatedLot = parkingLotService.updateParkingLot(id, updateDTO);
        return ResponseEntity.ok(updatedLot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingLot(@PathVariable Long id) {
        parkingLotService.deleteParkingLot(id);
        return ResponseEntity.noContent().build();
    }
}
