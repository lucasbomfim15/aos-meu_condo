package com.aos.meu_condo.modules.partyroom.model;

import com.aos.meu_condo.modules.condominium.model.Condominium;
import jakarta.persistence.*;

@Entity
@Table(name = "party_rooms")
public class PartyRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "condominium_id")
    private Condominium condominium;

    // === CONSTRUTORES ===

    /** Construtor padrão exigido pelo JPA */
    public PartyRoom() {
    }

    /**
     * Construtor completo (sem id, pois é gerado pelo JPA)
     */
    public PartyRoom(String name, int capacity, Condominium condominium) {
        this.name = name;
        this.capacity = capacity;
        this.condominium = condominium;
    }

    // === GETTERS E SETTERS ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }
}
