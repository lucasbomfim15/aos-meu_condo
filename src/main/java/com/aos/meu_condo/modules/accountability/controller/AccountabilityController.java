package com.aos.meu_condo.modules.accountability.controller;

import com.aos.meu_condo.modules.accountability.model.Accountability;
import com.aos.meu_condo.modules.accountability.repository.AccountabilityRepository;
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
    public Accountability criar(@RequestBody Accountability accountability) {
        return repository.save(accountability);
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
