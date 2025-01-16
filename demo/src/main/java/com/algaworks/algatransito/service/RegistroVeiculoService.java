package com.algaworks.algatransito.service;

import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.model.enumModel.StatusVeiculo;
import com.algaworks.algatransito.domain.model.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistroVeiculoService {

    private VeiculoRepository veiculoRepository;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {

        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }
}
