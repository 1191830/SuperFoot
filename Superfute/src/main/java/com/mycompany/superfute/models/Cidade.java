/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

 /**
 * @author nelso
 */
public class Cidade {
    

    private Pais pais;
    private int id;
    private String nome;

    

    public Cidade(Pais pais, int id, String nome) {
        this.pais = pais;
        this.id = id;
        this.nome = nome;
    }

    

    public Cidade() {
    }



    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
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
