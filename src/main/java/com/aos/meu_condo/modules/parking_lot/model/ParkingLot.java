package com.aos.meu_condo.modules.parking_lot.model; // <-- Confere o pacote

// Mantenha este import, vamos precisar depois
import com.aos.meu_condo.modules.condominium.model.Condominium; 

// Imports do Jakarta Persistence (para JPA funcionar)
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

// Imports do Lombok (se for usar)
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // Marca como tabela do banco
@Table(name = "tb_parking_lots") // Nome da tabela
@Data // Lombok: gera getters/setters/etc
@NoArgsConstructor // Lombok: gera construtor vazio
@AllArgsConstructor // Lombok: gera construtor cheio
public class ParkingLot {

    @Id // Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    private Long id;

    @Column(name = "spot_number", nullable = false, length = 10) // Coluna número da vaga
    private String spotNumber;

    @Column(length = 50) // Coluna bloco
    private String block;

    @Column(nullable = false) // Coluna ocupado
    private boolean occupied = false;

    @Column(name = "spot_type", length = 50) // Coluna tipo de vaga
    private String spotType;

    // Relacionamento: Muitas Vagas -> Um Condomínio
    @ManyToOne(fetch = FetchType.LAZY) // Define a relação N-1 (Lazy loading)
    @JoinColumn(name = "condominium_id", nullable = false) // Coluna da chave estrangeira nesta tabela
    private Condominium condominium; // Campo para guardar o objeto Condomínio relacionado
}