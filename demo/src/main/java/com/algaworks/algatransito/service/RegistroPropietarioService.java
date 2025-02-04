package com.algaworks.algatransito.service;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.exception.NegocioException;
import com.algaworks.algatransito.domain.model.repository.PropiettarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistroPropietarioService {

    private final PropiettarioRepository propiettarioRepository;

    public  Proprietario buscar(Long proprietarioId){
            return propiettarioRepository.findById(proprietarioId).orElseThrow(() ->
                new NegocioException(("Propietario nao encontrado")));
    }
    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        boolean emailInUse = propiettarioRepository.findByEmail(proprietario.getEmail()).filter(
                p -> !p.equals(proprietario)).isPresent();
        if(emailInUse){
            throw  new NegocioException("ja existe esse email");
        }
            return propiettarioRepository.save(proprietario);
    }

    @Transactional
    public void deletar( Long proprietarioId) {
         propiettarioRepository.deleteById(proprietarioId);
    }
}
