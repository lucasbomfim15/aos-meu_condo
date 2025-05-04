package com.aos.meu_condo.modules.apartment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aos.meu_condo.modules.apartment.model.Apartment;
import com.aos.meu_condo.modules.apartment.service.ApartmentService;
import com.aos.meu_condo.modules.condominium.model.Condominium;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @GetMapping("/condominium/{id}")
    public ResponseEntity<List<Apartment>> findByCondominium(@RequestBody Condominium condominium) {
        List<Apartment> apartments = apartmentService.findByCondominium(condominium);
        return ResponseEntity.ok().body(apartments);
    }

    @GetMapping("/")
    public ResponseEntity<List<Apartment>> findAll() {
        List<Apartment> apartments = apartmentService.findAll();
        return ResponseEntity.ok().body(apartments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> findById(@PathVariable Long id) {
        Apartment apartment = apartmentService.findById(id);
        return ResponseEntity.ok().body(apartment);
    }

    @PostMapping("/")
    public ResponseEntity<Apartment> create(@RequestBody Apartment apartment) {
        Apartment newApartment = apartmentService.create(apartment);
        return ResponseEntity.ok().body(newApartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apartment> update(@PathVariable Long id, @RequestBody Apartment apartment) {
        Apartment updatedApartment = apartmentService.update(id, apartment);
        return ResponseEntity.ok().body(updatedApartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Boolean deleted = apartmentService.delete(id);

        if (deleted) {
            return ResponseEntity.ok().body("Registro deletado com sucesso");
        }

        return ResponseEntity.badRequest().body("Registro nao existe");
    }

}
