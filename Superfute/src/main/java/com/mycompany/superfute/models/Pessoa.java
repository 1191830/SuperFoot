/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class Pessoa {

    private int id;
    private String nome;
    private Pais pais;
    private String nacionalidade;

    public Pessoa() {

    }
    
    //classe para apresentar dados principais
    public Pessoa(int id, String nome, String nacionalidade){
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", pais=" + pais +
                ", nacionalidade=" + nacionalidade + "}";
    }

    
}
