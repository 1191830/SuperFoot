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
    
    private Integer idJornada; // id da jornada
    private Integer idLiga; // id da liga

    /**
     * Construtor vazio de Jornada
     */
    public Jornada() {
    }

    /**
     * Construtor de Jornada com numero da jornada e ano da Liga
     * @param idJornada
     * @param idLiga 
     */
    public Jornada(Integer idJornada, Integer idLiga) {
        this.idJornada = idJornada;
        this.idLiga = idLiga;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Integer getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(Integer idLiga) {
        this.idLiga = idLiga;
    }

    @Override
    public String toString() {
        return "Jornada{" + "idJornada=" + idJornada + ", idLiga=" + idLiga + '}';
    }   
}
