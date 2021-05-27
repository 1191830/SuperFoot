/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

/**
 *
 * @author user
 */
public class Jornada {
    
    private int idJornada; // id da jornada
    private int idLiga; // id da liga

    public Jornada() {
    }

    public Jornada(int idJornada, int idLiga) {
        this.idJornada = idJornada;
        this.idLiga = idLiga;
    }

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    @Override
    public String toString() {
        return "Jornada{" + "idJornada=" + idJornada + ", idLiga=" + idLiga + '}';
    }
    
    
    
    
    
    
    
}
