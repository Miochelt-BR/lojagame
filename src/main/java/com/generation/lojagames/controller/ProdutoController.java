package com.generation.lojagames.controller;


import com.generation.lojagames.model.Produtos;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {


    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Produtos>> getall() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Produtos> postProdutos(@Valid @RequestBody Produtos produtos) {
        return categoriaRepository.findById(produtos.getCategorias().getId())
                .map(r -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtos)))
                .orElse(ResponseEntity.badRequest().build());

    }


    @PutMapping
    public ResponseEntity<Produtos> putProdutos(@Valid @RequestBody Produtos produtos) {
        if (produtoRepository.existsById(produtos.getId())) {
            return categoriaRepository.findById(produtos.getCategorias().getId())
                    .map(r -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtos)))
                    .orElse(ResponseEntity.badRequest().build());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable Long id){

        return produtoRepository.findById(id)
                .map(r -> {produtoRepository.deleteById(id); return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); })
                .orElse(ResponseEntity.notFound().build());
    }


    // CONSULTAS


    @GetMapping("/preco_maior/{preco}")
    public ResponseEntity<List<Produtos>>getPmaiorQ(@PathVariable BigDecimal preco){
        return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
    }


    @GetMapping("/preco_menor/{preco}")
    public ResponseEntity<List<Produtos>>getPmenorQ(@PathVariable BigDecimal preco) {
        return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));
    }

    //EXTRA
    @GetMapping("/preco_igual/{preco}")
    public ResponseEntity<List<Produtos>>getPigualA(@PathVariable BigDecimal preco) {
        return ResponseEntity.ok(produtoRepository.findByPrecoEqualsOrderByPreco(preco));
    }


    @GetMapping("/desenvolvedora_igual/{desenvolvedora}")
    public ResponseEntity<List<Produtos>>getDesEquals(@PathVariable String desenvolvedora){
        return ResponseEntity.ok(produtoRepository.findByDesenvolvedoraEquals(desenvolvedora));
    }



}
