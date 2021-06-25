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
    private String nacionalidade;
    private int golosMarcados;
    private String funcao;
    private int numExpulsoes;

    public Pessoa() {

    }

    public Pessoa(int id, String nome, String nacionalidade, String funcao,
            int numExpulsoes) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.funcao = funcao;
        this.numExpulsoes = numExpulsoes;
    }
    
    //classe para apresentar golos do jogador
    public Pessoa(String nome, int golos){
        this.nome = nome;
        this.golosMarcados = golos;
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

    public int getGolosMarcados() {
        return golosMarcados;
    }

    public void setGolosMarcados(int golosMarcados) {
        this.golosMarcados = golosMarcados;
    }

    public int getNumExpulsoes() {
        return numExpulsoes;
    }

    public void setNumExpulsoes(int numExpulsoes) {
        this.numExpulsoes = numExpulsoes;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", nacionalidade=" +
                nacionalidade + ", golosMarcados=" + golosMarcados + 
                ", funcao=" + funcao + ", numExpulsoes=" + numExpulsoes + '}';
    }
    
    

}
