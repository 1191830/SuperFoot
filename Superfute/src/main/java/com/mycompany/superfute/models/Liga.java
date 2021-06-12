/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

/**
 *
 * @author user
 */

public class Liga {
    
    private String nome; // Nome da liga
    private int ano; // ano da liga

    public Liga() {
    }

    public Liga(String nome, int ano) {
        this.nome = nome;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Liga{" + "nome=" + nome + ", ano=" + ano + '}';
    }
    
    
    
    
    
    
    
    
    
}
