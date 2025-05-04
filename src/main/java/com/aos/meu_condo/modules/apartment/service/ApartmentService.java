package com.aos.meu_condo.modules.apartment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aos.meu_condo.modules.apartment.model.Apartment;
import com.aos.meu_condo.modules.apartment.repository.ApartmentRepository;
import com.aos.meu_condo.modules.condominium.model.Condominium;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    public List<Apartment> findByCondominium(Condominium condominium) {
        List<Apartment> apartments = apartmentRepository.findByCondominium(condominium);
        return apartments;
    }

    public List<Apartment> findAll() {
        List<Apartment> apartments = apartmentRepository.findAll();
        return apartments;
    }

    public Apartment findById(Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return apartment;
    }

    public Apartment create(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    public Apartment update(Long id, Apartment apartment) {
        if (apartmentRepository.existsById(id)) {
            apartment.setId(id);
            return apartmentRepository.save(apartment);
        }

        return null;
    }

    public Boolean delete(Long id) {
        if (apartmentRepository.existsById(id)) {
            apartmentRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
