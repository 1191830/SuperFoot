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
    private int golosMarcados;
    private String funcao;
    private int numExpulsoes;
    private String nomeEquipa;
    private Equipa equipa;
    public ArrayList<Pessoa> listaJogadores;
    
    /**
     * Construtor vazio de pessoa
     */
    public Pessoa() {

    }

    public Pessoa(int id, String nome, String nacionalidade, String funcao,
            int numExpulsoes, String nomeEquipa) {

        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.funcao = funcao;
        this.numExpulsoes = numExpulsoes;
        this.nacionalidade = nacionalidade;
        this.nomeEquipa = nomeEquipa;
    }

    /**
     * Construtor de Pessoa com id nome e nacionalidade
     *
     * @param id
     * @param nome
     * @param nacionalidade
     */
    public Pessoa(int id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;

    }

    public ArrayList<Pessoa> getListaJogadores() {
        return listaJogadores;
    }

    public void setListaJogadores(ArrayList<Pessoa> listaJogadores) {
        this.listaJogadores = listaJogadores;
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

    public String getNomeEquipa() {
        return nomeEquipa;
    }

    public void setNomeEquipa(String nomeEquipa) {
        this.nomeEquipa = nomeEquipa;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

}
