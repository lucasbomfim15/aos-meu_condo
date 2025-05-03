package com.aos.meu_condo.modules.apartment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aos.meu_condo.modules.apartment.model.Apartment;
import com.aos.meu_condo.modules.apartment.model.Condominium;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> findByCondominium(Condominium condominium);

}
