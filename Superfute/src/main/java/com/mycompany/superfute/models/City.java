/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import com.mycompany.superfute.models.Country;

/**
 *
 * @author nelso
 */
public class City {
    
    private Country pais;
    private int id;
    private String nome;

    
    public City(Country pais, int id, String nome) {
        this.pais = pais;
        this.id = id;
        this.nome = nome;
    }

    public City() {
    }

    public Country getPais() {
        return pais;
    }

    public void setPais(Country pais) {
        this.pais = pais;
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
        return "Cidade{" + "pais=" + pais + ", id=" + id + ", nome=" + nome + '}';
    }
    
}
