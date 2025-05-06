package com.aos.meu_condo.modules.accountability.controller;

import com.aos.meu_condo.modules.accountability.dto.AccountabilityDTO;
import com.aos.meu_condo.modules.accountability.model.Accountability;
import com.aos.meu_condo.modules.accountability.repository.AccountabilityRepository;
import com.aos.meu_condo.modules.accountability.service.AccountabilityService;
import com.aos.meu_condo.modules.condominium.model.Condominium;
import com.aos.meu_condo.modules.condominium.repository.CondominiumRepository;
import com.aos.meu_condo.modules.user.model.User;
import com.aos.meu_condo.modules.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accountability")
public class AccountabilityController {

    @Autowired
    private AccountabilityRepository repository;

    @Autowired
    private AccountabilityService accountabilityService;

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Accountability> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accountability> buscarPorId(@PathVariable Long id) {
        Optional<Accountability> result = repository.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody AccountabilityDTO dto) {
        try {
            Accountability saved = accountabilityService.createAccountability(dto);
            return ResponseEntity.ok(saved);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accountability> atualizar(@PathVariable Long id, @RequestBody Accountability dados) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setDescricao(dados.getDescricao());
                    registro.setValor(dados.getValor());
                    registro.setData(dados.getData());
                    registro.setUser(dados.getUser());
                    registro.setCondominium(dados.getCondominium());
                    return ResponseEntity.ok(repository.save(registro));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
