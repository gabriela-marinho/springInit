package com.algaworks.algatransito.domain.model.repository;

import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropiettarioRepository  extends JpaRepository<Proprietario , Long> {

    /*
    O Spring Data Jpa fornece uma implementação autoutomaticamente e o codigo vai funcionar
    Existe uma padronizaçao no nome começa com um prefixo : FIND , depois o delimitado : by , depois a expressão
    ,a propriedade q irá usar da classe  Entity.
     */
    List<Proprietario> findByNomeContaining(String nome);
}
