package com.aos.meu_condo.modules.condominium.services;

import com.aos.meu_condo.modules.condominium.model.Condominium;
import com.aos.meu_condo.modules.condominium.repository.CondominiumRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CondominiumService {
    @Autowired
    private CondominiumRepository condominiumRepository;

    public Condominium create(Condominium condominium) {
        return condominiumRepository.save(condominium);
    }

    public Optional<Condominium> findbycnpj(String cnpj) {
        return condominiumRepository.findByCnpj(cnpj);
    }

    public Condominium updatecondo(Long id, Condominium updatecondominium) {
        if (!condominiumRepository.existsById(id)) {
            throw new RuntimeException("Condominium not found with id: " + id);
        }
        updatecondominium.setId(id);
        return condominiumRepository.save(updatecondominium);
    }

    public List<Condominium> findAll() {
        return condominiumRepository.findAll();
    }

    public void deleteById(Long id) {
        if (!condominiumRepository.existsById(id)) {
            throw new RuntimeException("Condominium not found with id: " + id);
        }
        condominiumRepository.deleteById(id);
    }

}
