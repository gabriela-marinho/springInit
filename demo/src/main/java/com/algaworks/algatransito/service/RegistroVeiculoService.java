package com.algaworks.algatransito.service;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.model.enumModel.StatusVeiculo;
import com.algaworks.algatransito.domain.model.exception.NegocioException;
import com.algaworks.algatransito.domain.model.repository.PropiettarioRepository;
import com.algaworks.algatransito.domain.model.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistroVeiculoService {

    private VeiculoRepository veiculoRepository;
    private PropiettarioRepository propiettarioRepository;
    private RegistroPropietarioService registroPropietarioService;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        if(novoVeiculo.getId() != null){
            throw  new NegocioException("Veículo cadastrado já possui um id, impossivel cadastrar");
        }
    boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca()).filter(veiculo ->
            !veiculo.equals(novoVeiculo)).isPresent();

    if(placaEmUso) {
        throw new NegocioException("Já existe um veículo cadastrado com esta placa!");
    }
        Proprietario propietario = registroPropietarioService.buscar(novoVeiculo.getProprietario().getId());
        novoVeiculo.setProprietario(propietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }
}
