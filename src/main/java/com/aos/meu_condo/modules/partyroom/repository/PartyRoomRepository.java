package com.aos.meu_condo.modules.partyroom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aos.meu_condo.modules.condominium.model.Condominium; // Import necessário
import com.aos.meu_condo.modules.partyroom.model.PartyRoom;

@Repository
public interface PartyRoomRepository extends JpaRepository<PartyRoom, Long> {

    // --- Método para corresponder ao exemplo do ApartmentService ---
    List<PartyRoom> findByCondominium(Condominium condominium); // <<< ADICIONADO

    // --- Funções Extras (Mantidas para cumprir requisito) ---
    List<PartyRoom> findByCondominiumId(Long condominiumId); // Pode coexistir

    @Query("SELECT pr FROM PartyRoom pr WHERE LOWER(pr.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<PartyRoom> findByNameContainingIgnoreCaseCustom(@Param("name") String name);

    boolean existsByNameAndCondominiumId(String name, Long condominiumId);

    Optional<PartyRoom> findByIdAndCondominiumId(Long id, Long condominiumId);

}