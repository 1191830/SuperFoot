/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

/**
 *
 * @author nelso
 */

public class Pais {
    
    private int id;
    private String nome;

    /**
     * Construtor vazio de Pais
     */
    public Pais() {
    }
    
    /**
     * Construtor Pais com id e nome do Pais
     * @param id
     * @param nome 
     */
    public Pais(int id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", nome=" + nome + '}';
    }  
}
