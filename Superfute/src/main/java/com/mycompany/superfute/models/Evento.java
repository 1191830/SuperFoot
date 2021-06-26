/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import com.mycompany.superfute.db.DbEvento;
import java.sql.SQLException;

/**
 *
 * @author bruno
 */
public class Evento {

    private int minuto;
    private int idJogo;
    private Equipa equipa;
    private Pessoa jogador;
    private int tipoEvento;
    private String evento;
    private int idParte;
    private String parte;
    public Evento() {

    }

    public Evento(int minuto, int idJogo, Equipa equipa, Pessoa jogador, int tipoEvento, int idParte) throws SQLException {
        this.minuto = minuto;
        this.idJogo = idJogo;
        this.equipa = equipa;
        this.jogador = jogador;
        this.tipoEvento = tipoEvento;
        this.evento = DbEvento.getEventoByTipo(tipoEvento);
        this.idParte = idParte;
        this.parte = DbEvento.getParteByIdParte(idParte);
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

    public Equipa getEquipa() {
        return equipa;
    }

    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
    }

    public Pessoa getJogador() {
        return jogador;
    }

    public void setJogador(Pessoa jogador) {
        this.jogador = jogador;
    }

    public int gettipoEvento() {
        return tipoEvento;
    }

    public void settipoEvento(int tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getIdParte() {
        return idParte;
    }

    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

    public String getEvento() {
        return evento;
    }

    public String getParte() {
        return parte;
    }
       
 @Override
    public String toString() {
        return "Evento{" + "minuto=" + minuto + ", id jogo=" + idJogo + ", id equipa=" + equipa + ", id jogador =" + jogador + ",tipo de evento"  + tipoEvento + '}';
    }
}
