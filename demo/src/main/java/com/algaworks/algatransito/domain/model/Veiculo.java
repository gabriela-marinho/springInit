package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.model.enumModel.StatusVeiculo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    // MUITOS VEICULOS PARA UM PROPRIETARIO
    @ManyToOne
    @JoinColumn
    private Proprietario proprietario;

    private String marca;

    private String modelo;

    private String placa;
    // O enumType.String diz que o tipo q irá ser armazenado é uma string
    //caso fosse o Ordinal no banco iria salvar a ordenação por numero
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    //apesar de no banco ser data_cadastro, o jakartaPersistence é inteligente
    // para saber que dataCadastro é igual a data_cadastro.
    private LocalDateTime dataCadastro;

    private LocalDateTime dataApreensao;

}
