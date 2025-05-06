package com.aos.meu_condo.modules.apartment.service;

import java.util.List;

import com.aos.meu_condo.modules.apartment.dtos.ApartmentDTO;
import com.aos.meu_condo.modules.condominium.repository.CondominiumRepository;
import com.aos.meu_condo.modules.user.model.User;
import com.aos.meu_condo.modules.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aos.meu_condo.modules.apartment.model.Apartment;
import com.aos.meu_condo.modules.apartment.repository.ApartmentRepository;
import com.aos.meu_condo.modules.condominium.model.Condominium;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    @Autowired
    private final CondominiumRepository condominiumRepository;

    @Autowired
    private final UserRepository userRepository;

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

    public Apartment create(ApartmentDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Condominium condominium = condominiumRepository.findById(dto.getCondominiumId())
                .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado"));

        Apartment apartment = new Apartment();
        apartment.setFloor(dto.getFloor());
        apartment.setNumber(dto.getNumber());
        apartment.setUser(user);
        apartment.setCondominium(condominium);

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
