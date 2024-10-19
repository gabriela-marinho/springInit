package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.repository.PropiettarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private PropiettarioRepository propiettarioRepository;

    @GetMapping
    public List<Proprietario> listar() {
        /*
        FAZ A CONSULTA DE TODAS AS INSTANCIAS Q TENHO NO REPOSITORIO
         */
        return propiettarioRepository.findAll();
    }
    /*
            o proprietarioId é o variavel de caminho (PATHVARIABLE)
            logo devemos anotar na rerquisição, fazendo o bind(o parametro do metodo á variavel da URI)
     */
    @GetMapping("/{proprietarioId}")
    /*
     O responseentity, representa a resposta q será retornada, mas com ele podemos MANIPULAR melhor a resposta .
     */
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId) {
        return propiettarioRepository.findById(proprietarioId).map(
                /*
                Method Reference da expressao lambda:
                ResponseEntity.ok(proprietario)
                 */
                ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

        /*
        o findById retorna um Optional, que diz que pode ter algo ou nao pode ter nada
         */
           //Optional<Proprietario> propietarioOptional= propiettarioRepository.findById(proprietarioId);
           /*
           aqui me diz q eu estou abrindo a caixinha, vendo se tem algo dentro se tiver eu pego com .get
            */
//        if(propietarioOptional.isPresent()){
//            return ResponseEntity.ok(propietarioOptional.get());
//        }
//        return ResponseEntity.notFound().build();


    }
}
