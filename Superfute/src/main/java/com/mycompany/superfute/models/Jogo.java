/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Estadio;
import com.mycompany.superfute.models.Jornada;
import com.mycompany.superfute.models.Liga;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Jogo {

    private Jogo Jogo; // id do jogo
    private Equipa EquipaCasa; //id da equipa da casa
    private String nomeCasa; //nome Equipa casa
    private Equipa EquipaFora; //id da equipa de Fora
    private String nomeFora; //nome Equipa fora
    private int golosCasa;
    private int golosFora;
    private LocalDate data; // data do jogo
    private Estadio Estadio; // id do Estadio
    private ArrayList<Evento> listEvento = new ArrayList();

    public Jogo() {
    }

    public Jogo(Jogo Jogo, Equipa EquipaCasa, Equipa EquipaFora, LocalDate data, Estadio Estadio) {
        this.Jogo = Jogo;
        this.EquipaCasa = EquipaCasa;
        this.EquipaFora = EquipaFora;
        this.data = data;
        this.Estadio = Estadio;
    }
    
    //Construtor para apresentar os jogos da jornada
    public Jogo(String casa, int golosCasa, String fora, int golosFora){
        this.nomeCasa = casa;
        this.golosCasa = golosCasa;
        this.nomeFora = fora;
        this.golosFora = golosFora;
        
    }

    public Jogo getJogo() {
        return Jogo;
    }

    public void setJogo(Jogo Jogo) {
        this.Jogo = Jogo;
    }

    public Equipa getEquipaCasa() {
        return EquipaCasa;
    }

    public void setEquipaCasa(Equipa EquipaCasa) {
        this.EquipaCasa = EquipaCasa;
    }

    public Equipa getEquipaFora() {
        return EquipaFora;
    }

    public void setEquipaFora(Equipa EquipaFora) {
        this.EquipaFora = EquipaFora;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Estadio getEstadio() {
        return Estadio;
    }

    public void setEstadio(Estadio Estadio) {
        this.Estadio = Estadio;
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public String getNomeFora() {
        return nomeFora;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }

    public void setNomeCasa(String nomeCasa) {
        this.nomeCasa = nomeCasa;
    }

    public void setNomeFora(String nomeFora) {
        this.nomeFora = nomeFora;
    }
    
    

    @Override
    public String toString() {
        return "Jogo{" + "Jogo=" + Jogo + ", EquipaCasa=" + EquipaCasa +
                ", EquipaFora=" + EquipaFora + ", data=" + data + ", Estadio="
                + Estadio + ", listEvento=" + listEvento + '}';
    }

    public Object getLiga() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
