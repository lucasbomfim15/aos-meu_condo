package com.aos.meu_condo.modules.partyroom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aos.meu_condo.modules.condominium.model.Condominium;
import com.aos.meu_condo.modules.partyroom.model.PartyRoom;
import com.aos.meu_condo.modules.partyroom.repository.PartyRoomRepository;

@Service
public class PartyRoomService {

    private final PartyRoomRepository partyRoomRepository;

    // === Construtor manual para injeção ===
    public PartyRoomService(PartyRoomRepository partyRoomRepository) {
        this.partyRoomRepository = partyRoomRepository;
    }

    // Método para listar todas as party rooms
    public List<PartyRoom> findAll() {
        return partyRoomRepository.findAll();
    }

    // Método para buscar por ID
    public PartyRoom findById(Long id) {
        return partyRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PartyRoom não encontrado com id: " + id));
    }

    // Método para buscar por Condominium
    public List<PartyRoom> findByCondominium(Condominium condominium) {
        return partyRoomRepository.findByCondominium(condominium);
    }

    // Método para buscar por Condominium ID
    public List<PartyRoom> findByCondominiumIdService(Long condominiumId) {
        return partyRoomRepository.findByCondominiumId(condominiumId);
    }

    // Método para criar
    public PartyRoom create(PartyRoom partyRoom) {
        return partyRoomRepository.save(partyRoom);
    }

    // Método para atualizar
    public PartyRoom update(Long id, PartyRoom partyRoom) {
        if (partyRoomRepository.existsById(id)) {
            partyRoom.setId(id);
            return partyRoomRepository.save(partyRoom);
        }
        return null;
    }

    // Método para deletar
    public Boolean delete(Long id) {
        if (partyRoomRepository.existsById(id)) {
            partyRoomRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
