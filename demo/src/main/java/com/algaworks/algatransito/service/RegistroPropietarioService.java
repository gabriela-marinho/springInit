package com.algaworks.algatransito.service;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.repository.PropiettarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistroPropietarioService {

    private final PropiettarioRepository propiettarioRepository;

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
            return propiettarioRepository.save(proprietario);
    }

    @Transactional
    public void deletar( Long proprietarioId) {
         propiettarioRepository.deleteById(proprietarioId);
    }
}
