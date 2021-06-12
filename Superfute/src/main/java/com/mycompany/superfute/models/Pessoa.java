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
abstract class Pessoa {

    private int id;
    private String nome;
    private String nacionalidade;
    private String funcao;

    public Pessoa() {

    }

    public Pessoa(int id, String nome, String nacionalidade, String funcao) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
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

    public String getnacionalidade() {
        return nacionalidade;
    }

    public void setnacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
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
                + nacionalidade + ",funçao" + funcao + '}';
    }
}
