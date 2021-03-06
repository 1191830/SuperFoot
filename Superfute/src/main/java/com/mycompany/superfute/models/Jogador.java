/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import java.time.LocalDate;

/**
 *
 * @author nelso
 */
public class Jogador extends Pessoa {
    
    private int golosMarcados;
    private int golosAnulados;
    private int amarelos;
    private int duploAmarelo;
    private int vemelho;
    private int idFuncao;
    private String funcao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int qtdJogos;
    

     public Jogador() {
    }

    public int getQtdJogos() {
        return qtdJogos;
    }

    public void setQtdJogos(int qtdJogos) {
        this.qtdJogos = qtdJogos;
    }

  
    
    public int getGolosMarcados() {
        return golosMarcados;
    }

    public void setGolosMarcados(int golosMarcados) {
        this.golosMarcados = golosMarcados;
    }
     public int getGolosAnulados() {
        return golosAnulados;
    }

    public void setGolosAnulados(int golosAnulados) {
        this.golosAnulados = golosAnulados;
    }

    public int getAmarelos() {
        return amarelos;
    }

    public void setAmarelos(int amarelos) {
        this.amarelos = amarelos;
    }

    public int getDuploAmarelo() {
        return duploAmarelo;
    }

    public void setDuploAmarelo(int duploAmarelo) {
        this.duploAmarelo = duploAmarelo;
    }

    public int getVemelho() {
        return vemelho;
    }

    public void setVemelho(int vemelho) {
        this.vemelho = vemelho;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public int getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Jogador{" + "golosMarcados=" + golosMarcados 
                + ", golosAnulados=" + golosAnulados + ", amarelos="
                + amarelos + ", duploAmarelo=" + duploAmarelo
                + ", vemelho=" + vemelho + ", idFuncao=" + idFuncao
                + ", funcao=" + funcao + ", dataInicio=" + dataInicio 
                + ", dataFim=" + dataFim + ", qtdJogos=" + qtdJogos + '}';
    }
    
    


    
    
    
}
