package com.aos.meu_condo.modules.accountability.service;

import com.aos.meu_condo.modules.accountability.dto.AccountabilityDTO;
import com.aos.meu_condo.modules.accountability.model.Accountability;
import com.aos.meu_condo.modules.accountability.repository.AccountabilityRepository;
import com.aos.meu_condo.modules.condominium.model.Condominium;
import com.aos.meu_condo.modules.condominium.repository.CondominiumRepository;
import com.aos.meu_condo.modules.condominium.services.CondominiumService;
import com.aos.meu_condo.modules.user.model.User;
import com.aos.meu_condo.modules.user.repository.UserRepository;
import com.aos.meu_condo.modules.user.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AccountabilityService {

    @Autowired
    private AccountabilityRepository accountabilityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CondominiumRepository condominiumRepository;

    public Accountability createAccountability(AccountabilityDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Condominium condominium = condominiumRepository.findById(dto.getCondominiumId())
                .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado"));

        Accountability accountability = new Accountability();
        accountability.setDescricao(dto.getDescricao());
        accountability.setValor(dto.getValor());
        accountability.setData(dto.getData());
        accountability.setUser(user);
        accountability.setCondominium(condominium);

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
