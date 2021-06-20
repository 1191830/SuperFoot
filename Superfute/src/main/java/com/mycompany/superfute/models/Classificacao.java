/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

/**
 *
 * @author Rui
 */
public class Classificacao {
    
    private String equipa;
    private int jogos;
    private int pontos;
    private int golosMarcados;
    private int golosSofridos;
    private int vitorias;
    private int empates;
    private int derrotas;

    public Classificacao() {
    }

    public Classificacao(String equipa, int pontos, int golosMarcados, int golosSofridos) {
        this.equipa = equipa;
        this.pontos = pontos;
        this.golosMarcados = golosMarcados;
        this.golosSofridos = golosSofridos;
        
    }
    
    public Classificacao(String equipa,int golosMarcados) {
        this.equipa = equipa;
        this.golosMarcados = golosMarcados;              
    }

    public String getEquipa() {
        return equipa;
    }

    public int getJogos() {
        return jogos;
    }
    
    public int getPontos() {
        return pontos;
    }

    public int getGolosMarcados() {
        return golosMarcados;
    }

    public int getGolosSofridos() {
        return golosSofridos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setEquipa(String equipa) {
        this.equipa = equipa;
    }

    public void setJogos(int jogos) {
        this.jogos = jogos;
    }
    
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setGolosMarcados(int golosMarcados) {
        this.golosMarcados = golosMarcados;
    }

    public void setGolosSofridos(int golosSofridos) {
        this.golosSofridos = golosSofridos;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
    
    
    
    
}
