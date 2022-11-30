package com.generation.lojagames.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_categorias")
public class Categorias {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Tipo da categoria e obrigatoria ! ")
    @Size(min = 3, max = 10, message = "Digite no minimo 3 caracteres e no maximo 10")
    private String tipo;



    @OneToMany(mappedBy = "categorias", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categorias")
    private List<Produtos> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }
}
