package com.generation.lojagames.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name =  "tb_produtos")
public class Produtos {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // PRECO DO PRODUTO
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "Preço é um valor obrigatorio ! ")
    @Positive(message = "Voce deve digitar um valor positivo maior que zero ! ")
    private BigDecimal preco;


    // QUANTIDADE DE PRODUTOS
    private int quantidade;



    // NOME DO PRODUTO
    @NotNull(message = "Nome é obrigatorio ! ")
    @Size(min = 3 , max = 20 , message = "Numero de caracteres permitido minimo de 3 e maximo de 20.")
    private String nome;


    // DESENVOLVEDORA
    @NotNull(message = "Desenvolvedora é obrigatorio !")
    @Size(min = 3 , max = 20 , message = "Numero de caracteres permitido minimo de 3 e maximo de 20.")
    private String desenvolvedora;


    // DATA DE LANÇAMENTO
    @Column(name = "data_lancamento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLancamento;


    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Categorias categorias;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }
}
