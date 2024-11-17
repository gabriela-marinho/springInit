package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.repository.PropiettarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario) {
        //como estamos passando no corpo os dados do propietario, devemos passar
        // via parametro o propietario
        //@RequestBody vai vincular o parametro ao corpo da requisição, ou seja
        //oq eu passar no corpo da requisição vai ficar vinculado ao argumento, ele vai desserializar
        //(vai pegar pegar o jason e transformar em um objeto java do tipo propietario)
       return propiettarioRepository.save(proprietario);
       //já faço o return ja salvando
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long id,
                                                  @RequestBody Proprietario proprietario) {
        //se colocamos um pathVariable na anotation é importante fazer um BIND unindo ela ao
        //parametro do metodo, vincunlando os 2.
        //álem disso ,vou precisar transformar o corpo d a minha requisição em um objeto,
        // por isso uso o RequestBody

        if(propiettarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();

            //confere se o recurso existe
        }
        proprietario.setId(id);
        Proprietario propietarioAtualizado = propiettarioRepository.save(proprietario);
        return  ResponseEntity.ok(propietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    //Não vou precisar Tipar o responseEntity, pois o delete nao precia de um corpo no retorno da mens
    // agem.
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
        if(!propiettarioRepository.existsById(proprietarioId)) {
            //aqui retono o não encontrado 404
                return ResponseEntity.notFound().build();
        }
        propiettarioRepository.deleteById(proprietarioId);
        //aqui retorno o 204 no content, foi edcluido e nao há oq retornar
        return ResponseEntity.noContent().build();
    }
}
