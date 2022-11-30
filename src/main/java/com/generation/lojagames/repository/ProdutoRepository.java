package com.generation.lojagames.repository;

import com.generation.lojagames.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Long> {


    public List<Produtos>findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
    public List <Produtos> findByPrecoGreaterThanOrderByPreco (BigDecimal preco);
    public List<Produtos> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);

    //EXTRA
    public List<Produtos> findByPrecoEqualsOrderByPreco(BigDecimal preco);
    public List<Produtos> findByDesenvolvedoraEquals(String desenvolvedora);
}
