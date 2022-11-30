package com.generation.lojagames.controller;


import com.generation.lojagames.model.Categorias;
import com.generation.lojagames.repository.CategoriaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {



        @Autowired
        private CategoriaRepository categoriaRepository;




        @GetMapping
        private ResponseEntity<List<Categorias>> getAll(){

            return ResponseEntity.ok(categoriaRepository.findAll());

        }


        @GetMapping("/{id}")
        private ResponseEntity<Categorias> getById(@PathVariable Long id){


            return categoriaRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }


        @GetMapping("/tipo/{tipo}") // LISTAR OU TRAZER DADOS
        public ResponseEntity<List<Categorias>> getByTipo(@PathVariable String tipo){
            return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));

        }
        @PostMapping // CRIAR OU POSTAR
        public ResponseEntity<Categorias> postCategorias(@Valid @RequestBody Categorias categorias){

            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categorias));

        }

        @PutMapping //ATUALIZAR
        public ResponseEntity<Categorias>putCategorias(@Valid @RequestBody Categorias categorias){

            return categoriaRepository.findById(categorias.getId())
                    .map(r -> ResponseEntity.ok().body(categoriaRepository.save(categorias)))
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        private ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
            return categoriaRepository.findById(id)

                    .map(r -> {
                        categoriaRepository.deleteById(id);
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                    })
                    .orElse(ResponseEntity.notFound().build());
        }


}
