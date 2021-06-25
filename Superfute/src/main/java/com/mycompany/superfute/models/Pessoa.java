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
    private String funcao;

    public Pessoa() {

    }

    public Pessoa(int id, String nome, Pais pais, String funcao) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.funcao = funcao;
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

    public String getfuncao() {
        return funcao;
    }

    public void setfuncao(String funcao) {
        this.funcao = funcao;
    }

     @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", nacionalidade=" 
                + pais + ",funçao" + funcao + '}';
    }
}
