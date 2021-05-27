/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author user
 */
public class Jogo {
    
    private int idLiga; // id da liga
    private int idJogo; // id do jogo
    private int idJornada; // id da jornada
    private int idEquipaCasa; //id da equipa da casa
    private int idEquipaFora; //id da equipa de Fora 
    private LocalDate data; // data do jogo
    private int tempoExtra; // tempo extra em minutos
    private int idEstadio; // id do Estadio

    public Jogo() {
    }

    public Jogo(int idLiga, int idJogo, int idJornada, int idEquipaCasa, int idEquipaFora, LocalDate data, int tempoExtra, int idEstadio) {
        this.idLiga = idLiga;
        this.idJogo = idJogo;
        this.idJornada = idJornada;
        this.idEquipaCasa = idEquipaCasa;
        this.idEquipaFora = idEquipaFora;
        this.data = data;
        this.tempoExtra = tempoExtra;
        this.idEstadio = idEstadio;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public int getIdEquipaCasa() {
        return idEquipaCasa;
    }

    public void setIdEquipaCasa(int idEquipaCasa) {
        this.idEquipaCasa = idEquipaCasa;
    }

    public int getIdEquipaFora() {
        return idEquipaFora;
    }

    public void setIdEquipaFora(int idEquipaFora) {
        this.idEquipaFora = idEquipaFora;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getTempoExtra() {
        return tempoExtra;
    }

    public void setTempoExtra(int tempoExtra) {
        this.tempoExtra = tempoExtra;
    }

    public int getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    @Override
    public String toString() {
        return "Jogo{" + "idLiga=" + idLiga + ", idJogo=" + idJogo + ", idJornada=" + idJornada + ", idEquipaCasa=" + idEquipaCasa + ", idEquipaFora=" + idEquipaFora + ", data=" + data + ", tempoExtra=" + tempoExtra + ", idEstadio=" + idEstadio + '}';
    }
    
    
    
    
    
    
    
    
    
}
