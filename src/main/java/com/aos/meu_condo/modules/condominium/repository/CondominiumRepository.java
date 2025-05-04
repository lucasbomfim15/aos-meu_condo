package com.aos.meu_condo.modules.condominium.repository;

import com.aos.meu_condo.modules.condominium.model.Condominium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominiumRepository extends JpaRepository<Condominium, Long> {
    Optional<Condominium> findByCnpj(String cnpj);
}
