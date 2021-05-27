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
public class Evento {

    private int minuto;
    private int idJogo;
    private int idEquipa;
    private int idJogador;
    private int tipoEvento;

    public Evento() {

    }

    public Evento(int minuto, int idJogo, int idEquipa, int idJogador, int tipoEvento) {
        this.minuto = minuto;
        this.idJogo = idJogo;
        this.idEquipa = idEquipa;
        this.idJogador = idJogador;
        this.tipoEvento = tipoEvento;
    }

    public int getminuto() {
        return minuto;
    }

    public void setminuto(int minuto) {
        this.minuto = minuto;
    }

    public int getidJogo() {
        return idJogo;
    }

    public void setidJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(int idEquipa) {
        this.idEquipa = idEquipa;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public int gettipoEvento() {
        return tipoEvento;
    }

    public void settipoEvento(int tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
 @Override
    public String toString() {
        return "Evento{" + "minuto=" + minuto + ", id jogo=" + idJogo + ", id equipa=" + idEquipa + ", id jogador =" + idJogador + ",tipo de evento"  + tipoEvento + '}';
    }
}
