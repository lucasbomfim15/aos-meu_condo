package com.aos.meu_condo.modules.partyroom.controller;

import com.aos.meu_condo.modules.partyroom.model.PartyRoom;
import com.aos.meu_condo.modules.partyroom.service.PartyRoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Import geral pode simplificar

import java.util.List;

@RestController
@RequestMapping("/partyrooms") // Base path
public class PartyRoomController {

    private final PartyRoomService partyRoomService;

    public PartyRoomController(PartyRoomService partyRoomService) {
        this.partyRoomService = partyRoomService;
    }


    @GetMapping
    public ResponseEntity<List<PartyRoom>> findAll() {
        List<PartyRoom> partyRooms = partyRoomService.findAll();
        return ResponseEntity.ok(partyRooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyRoom> findById(@PathVariable Long id) {

        PartyRoom partyRoom = partyRoomService.findById(id);
        return ResponseEntity.ok(partyRoom);
    }

    @GetMapping("/condominium/{condominiumId}")
    public ResponseEntity<List<PartyRoom>> findByCondominiumId(@PathVariable Long condominiumId) {
        // TODO: Idealmente, verificar se Condominium com esse ID existe primeiro
        List<PartyRoom> partyRooms = partyRoomService.findByCondominiumIdService(condominiumId);
        return ResponseEntity.ok(partyRooms);
    }

    // === CORRIGIDO: Removido o "/" ===
    @PostMapping // Agora mapeia para POST /partyrooms
    public ResponseEntity<PartyRoom> create(@Valid @RequestBody PartyRoom partyRoom) {
        // O ID será gerado pelo banco ao salvar
        // O objeto partyRoom recebido deve ter um objeto Condominium com um ID válido
        PartyRoom newPartyRoom = partyRoomService.create(partyRoom);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPartyRoom); // Melhor retornar 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyRoom> update(@PathVariable Long id, @Valid @RequestBody PartyRoom partyRoomDetails) {
        PartyRoom updatedPartyRoom = partyRoomService.update(id, partyRoomDetails);
        if (updatedPartyRoom != null) {
            return ResponseEntity.ok(updatedPartyRoom);
        } else {
            // Retornar 404 se o ID original não existia
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // .build() para corpo vazio
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Boolean deleted = partyRoomService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Registro deletado com sucesso");
        } else {
            // Segue o exemplo, mas 404 seria mais apropriado que 400 aqui
            return ResponseEntity.badRequest().body("Registro nao existe");
        }
    }
}