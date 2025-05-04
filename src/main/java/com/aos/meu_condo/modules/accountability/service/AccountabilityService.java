package com.aos.meu_condo.modules.accountability.service;

import com.aos.meu_condo.modules.accountability.model.Accountability;
import com.aos.meu_condo.modules.accountability.repository.AccountabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AccountabilityService {

    @Autowired
    private AccountabilityRepository accountabilityRepository;

    public Accountability createAccountability(Accountability accountability) {
        return accountabilityRepository.save(accountability);
    }

    public List<Accountability> getAllAccountabilities() {
        return accountabilityRepository.findAll();
    }

    public Accountability getAccountabilityById(Long id) {
        return accountabilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Accountability not found"));
    }

    public List<Accountability> getAccountabilitiesByDescricao(String descricao) {
        return accountabilityRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public List<Accountability> getAccountabilitiesByDataRange(LocalDate inicio, LocalDate fim) {
        return accountabilityRepository.findByDataBetween(inicio, fim);
    }

    public List<Accountability> getAccountabilitiesByUserId(Long userId) {
        return accountabilityRepository.findByUserUserId(userId);
    }

    public List<Accountability> getAccountabilitiesByCondominiumId(Long condominiumId) {
        return accountabilityRepository.findByCondominiumId(condominiumId);
    }

    public List<Accountability> getAccountabilitiesByValorGreaterThan(Double valor) {
        return accountabilityRepository.findByValorGreaterThan(valor);
    }

    public Accountability updateAccountability(Long id, Accountability accountability) {
        Accountability existingAccountability = getAccountabilityById(id);
        existingAccountability.setDescricao(accountability.getDescricao());
        existingAccountability.setValor(accountability.getValor());
        existingAccountability.setData(accountability.getData());
        existingAccountability.setUser(accountability.getUser());
        existingAccountability.setCondominium(accountability.getCondominium());
        return accountabilityRepository.save(existingAccountability);
    }

    public void deleteAccountability(Long id) {
        accountabilityRepository.deleteById(id);
    }
}
