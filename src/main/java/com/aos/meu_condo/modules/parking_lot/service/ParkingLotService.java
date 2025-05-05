package com.aos.meu_condo.modules.parking_lot.service;

import com.aos.meu_condo.modules.parking_lot.dto.ParkingLotCreateDTO;
import com.aos.meu_condo.modules.parking_lot.dto.ParkingLotResponseDTO;
import com.aos.meu_condo.modules.parking_lot.model.ParkingLot;
import com.aos.meu_condo.modules.parking_lot.repository.ParkingLotRepository;
import com.aos.meu_condo.modules.condominium.model.Condominium;
import com.aos.meu_condo.modules.condominium.repository.CondominiumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Transactional
    public ParkingLotResponseDTO createParkingLot(ParkingLotCreateDTO createDTO) {
        Condominium condo = condominiumRepository.findById(createDTO.getCondominiumId())
                .orElseThrow(() -> new EntityNotFoundException("Condominium not found with ID: " + createDTO.getCondominiumId()));

        parkingLotRepository.findBySpotNumberAndCondominiumId(createDTO.getSpotNumber(), createDTO.getCondominiumId())
                .ifPresent(existingLot -> {
                    throw new IllegalArgumentException("Parking spot number '" + createDTO.getSpotNumber() + "' already exists in this condominium.");
                });

        ParkingLot newLot = new ParkingLot();
        newLot.setSpotNumber(createDTO.getSpotNumber());
        newLot.setBlock(createDTO.getBlock());
        newLot.setOccupied(createDTO.getOccupied());
        newLot.setSpotType(createDTO.getSpotType());
        newLot.setCondominium(condo);

        ParkingLot savedLot = parkingLotRepository.save(newLot);
        return mapToResponseDTO(savedLot);
    }

    @Transactional(readOnly = true)
    public List<ParkingLotResponseDTO> getAllParkingLots() {
        return parkingLotRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ParkingLotResponseDTO getParkingLotById(Long id) {
        ParkingLot lot = parkingLotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parking Lot not found with ID: " + id));
        return mapToResponseDTO(lot);
    }

    @Transactional(readOnly = true)
    public List<ParkingLotResponseDTO> getParkingLotsByCondominiumId(Long condominiumId) {
        if (!condominiumRepository.existsById(condominiumId)) {
            throw new EntityNotFoundException("Condominium not found with ID: " + condominiumId);
        }
        return parkingLotRepository.findByCondominiumId(condominiumId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ParkingLotResponseDTO> getAvailableParkingLotsByCondominium(Long condominiumId) {
        if (!condominiumRepository.existsById(condominiumId)) {
            throw new EntityNotFoundException("Condominium not found with ID: " + condominiumId);
        }
        return parkingLotRepository.findAllAvailableSpotsByCondominium(condominiumId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ParkingLotResponseDTO updateParkingLot(Long id, ParkingLotCreateDTO updateDTO) {
        ParkingLot existingLot = parkingLotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parking Lot not found with ID: " + id));

        if (!existingLot.getCondominium().getId().equals(updateDTO.getCondominiumId())) {
            Condominium newCondo = condominiumRepository.findById(updateDTO.getCondominiumId())
                    .orElseThrow(() -> new EntityNotFoundException("New Condominium for update not found with ID: " + updateDTO.getCondominiumId()));
            existingLot.setCondominium(newCondo);
        }

        if (!existingLot.getSpotNumber().equalsIgnoreCase(updateDTO.getSpotNumber())) {
            parkingLotRepository.findBySpotNumberAndCondominiumId(updateDTO.getSpotNumber(), existingLot.getCondominium().getId())
                    .ifPresent(anotherLot -> {
                        if (!anotherLot.getId().equals(existingLot.getId())) {
                            throw new IllegalArgumentException("Parking spot number '" + updateDTO.getSpotNumber() + "' already exists for another spot in this condominium.");
                        }
                    });
            existingLot.setSpotNumber(updateDTO.getSpotNumber());
        }

        existingLot.setBlock(updateDTO.getBlock());
        existingLot.setOccupied(updateDTO.getOccupied());
        existingLot.setSpotType(updateDTO.getSpotType());

        ParkingLot updatedLot = parkingLotRepository.save(existingLot);
        return mapToResponseDTO(updatedLot);
    }

    @Transactional
    public void deleteParkingLot(Long id) {
        if (!parkingLotRepository.existsById(id)) {
            throw new EntityNotFoundException("Parking Lot not found with ID: " + id + ", cannot delete.");
        }
        parkingLotRepository.deleteById(id);
    }

    private ParkingLotResponseDTO mapToResponseDTO(ParkingLot lot) {
        ParkingLotResponseDTO dto = new ParkingLotResponseDTO();
        dto.setId(lot.getId());
        dto.setSpotNumber(lot.getSpotNumber());
        dto.setBlock(lot.getBlock());
        dto.setOccupied(lot.isOccupied());
        dto.setSpotType(lot.getSpotType());
        if (lot.getCondominium() != null) {
            dto.setCondominiumId(lot.getCondominium().getId());
        } else {
            dto.setCondominiumId(null);
        }
        return dto;
    }
}
