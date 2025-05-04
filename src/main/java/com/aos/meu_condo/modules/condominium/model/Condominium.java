package com.aos.meu_condo.modules.condominium.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Data
@Entity
public class Condominium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 14, unique = true)
    private String cnpj;
    @Column(length = 255)
    private String address;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(length = 20)
    private String telephone;


}
