/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

/**
 *
 * @author bruno
 */
public class Pessoa {

    private int id;
    private String nome;
    private Pais pais;
    private String nacionalidade;

    
    /**
     * Construtor vazio de pessoa
     */
    public Pessoa() {

    }
    /**
     * Construtor de Pessoa com id nome e nacionalidade
     * @param id
     * @param nome
     * @param nacionalidade 
     */
    public Pessoa(int id, String nome, String nacionalidade){
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        
    }
    
    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", pais=" + pais +
                ", nacionalidade=" + nacionalidade + "}";
    }
}
