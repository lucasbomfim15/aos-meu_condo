package com.aos.meu_condo.modules.accountability.repository;

import com.aos.meu_condo.modules.accountability.model.Accountability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AccountabilityRepository extends JpaRepository<Accountability, Long> {
    List<Accountability> findByDescricaoContainingIgnoreCase(String descricao);
    List<Accountability> findByDataBetween(LocalDate inicio, LocalDate fim);
    List<Accountability> findByUserUserId(Long userId);
    List<Accountability> findByCondominiumId(Long condominiumId);
    List<Accountability> findByValorGreaterThan(Double valor);
}
