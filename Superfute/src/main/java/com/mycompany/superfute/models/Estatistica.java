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
public class Estatistica {
    
    private int anoLiga;
    private int numJornada;
    private int Gol;
    private int golSofrido;
    private int amarelo;
    private int amareloDuplo;
    private int vermelho;
    private int ponto;
    private boolean casaFora;
    private String resultado;

    public int getAnoLiga() {
        return anoLiga;
    }

    public void setAnoLiga(int anoLiga) {
        this.anoLiga = anoLiga;
    }

    public int getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public int getGol() {
        return Gol;
    }

    public void setGol(int Gol) {
        this.Gol = Gol;
    }

    public int getGolSofrido() {
        return golSofrido;
    }

    public void setGolSofrido(int golSofrido) {
        this.golSofrido = golSofrido;
    }

    public int getAmarelo() {
        return amarelo;
    }

    public void setAmarelo(int amarelo) {
        this.amarelo = amarelo;
    }

    public int getAmareloDuplo() {
        return amareloDuplo;
    }

    public void setAmareloDuplo(int amareloDuplo) {
        this.amareloDuplo = amareloDuplo;
    }

    public int getVermelho() {
        return vermelho;
    }

    public void setVermelho(int vermelho) {
        this.vermelho = vermelho;
    }

    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    public boolean isCasaFora() {
        return casaFora;
    }

    public void setCasaFora(boolean casaFora) {
        this.casaFora = casaFora;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getCasaForaTexto(){
        
        return this.isCasaFora() == true ? "Casa" : "Fora";
    }
    public Estatistica() {
    }
    
    
}
