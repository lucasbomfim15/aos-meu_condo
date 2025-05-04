package com.aos.meu_condo.modules.condominium.controller;

import com.aos.meu_condo.modules.condominium.model.Condominium;
import com.aos.meu_condo.modules.condominium.services.CondominiumService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/condominium")
public class CondominiumController {
    private final CondominiumService condominiumService;

    @GetMapping("/")
    public ResponseEntity<List<Condominium>> getAll() {
        List<Condominium> condominiums = condominiumService.findAll();
        return ResponseEntity.ok(condominiums);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Condominium> getByCnpj(@PathVariable String cnpj) {
        return condominiumService.findbycnpj(cnpj)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Condominium> create(@RequestBody Condominium condominium) {
        Condominium created = condominiumService.create(condominium);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Condominium> update(@PathVariable Long id, @RequestBody Condominium condominium) {
        try {
            Condominium update = condominiumService.updatecondo(id, condominium);
            return ResponseEntity.ok(update);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Condominium> delete(@PathVariable Long id) {
        try {
            condominiumService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
