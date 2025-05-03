package com.aos.meu_condo.modules.parking_lot.repository;

import com.aos.meu_condo.modules.parking_lot.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    List<ParkingLot> findByCondominiumId(Long condominiumId);

    Optional<ParkingLot> findBySpotNumberAndCondominiumId(String spotNumber, Long condominiumId);

    @Query("SELECT pl FROM ParkingLot pl WHERE pl.condominium.id = :condoId AND pl.occupied = false ORDER BY pl.spotNumber")
    List<ParkingLot> findAllAvailableSpotsByCondominium(@Param("condoId") Long condominiumId);
}
