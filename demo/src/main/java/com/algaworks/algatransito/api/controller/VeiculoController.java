package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.model.exception.NegocioException;
import com.algaworks.algatransito.domain.model.repository.VeiculoRepository;
import com.algaworks.algatransito.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;

    @GetMapping
    public List<Veiculo> listar(){
           return veiculoRepository.findAll();
    }

    /*
           o proprietarioId é a variavel de caminho (PATHVARIABLE)
           logo devemos anotar na requisição, fazendo o bind(do parametro do metodo á variavel da URI)
    */
    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscar(@PathVariable Long veiculoId){
        return veiculoRepository.findById(veiculoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

//************OUTRA FORMA ABAIXO****************

        //ao inves de usar o orElse
        //if(propietarioOptional.isPresent()){
//            return ResponseEntity.ok(propietarioOptional.get());
//        }
//        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo){
            return  registroVeiculoService.cadastrar(veiculo);

    }

    //exceção foi para classe ExceptionHandler

}
