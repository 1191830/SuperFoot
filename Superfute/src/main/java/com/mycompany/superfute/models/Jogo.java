/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Jogo {

    private int jogo; // id do jogo
    private Jornada jornada;
    private Equipa EquipaCasa; //id da equipa da casa
    private String nomeCasa; //nome Equipa casa
    private Equipa EquipaFora; //id da equipa de Fora
    private String nomeFora; //nome Equipa fora
    private int golosCasa; //golos da equipa da casa
    private int golosFora; //golos da equipa visitante
    private LocalDateTime data; // data do jogo
    private Pessoa arbitro; //arbitro do Jogo
    private Estadio Estadio; // id do Estadio
    private ArrayList<Evento> listEvento = new ArrayList();

    /**
     * Construtor vazio de jogo
     */
    public Jogo() {
    }
    
    /**
     * Construtor de Jogo com Jogo, Jornada, Estadio e Arbitro
     * @param jogo
     * @param jornada
     * @param Estadio
     * @param arbitro 
     */
    public Jogo(Integer jogo, Jornada jornada, Estadio Estadio, Pessoa arbitro) {
        this.jogo = jogo;
        this.jornada = jornada;
        this.Estadio = Estadio;
        this.arbitro = arbitro;
    }

    public Jogo(Integer jogo, Equipa EquipaCasa, Equipa EquipaFora, LocalDateTime data, Estadio Estadio) {
        this.jogo = jogo;
        this.EquipaCasa = EquipaCasa;
        this.EquipaFora = EquipaFora;
        this.data = data;
        this.Estadio = Estadio;
    }
    
    //Construtor para apresentar os jogos da jornada
    public Jogo(int jogo, String casa, int golosCasa, String fora, int golosFora){
        this.jogo = jogo;
        this.nomeCasa = casa;
        this.golosCasa = golosCasa;
        this.nomeFora = fora;
        this.golosFora = golosFora;
        
    }
    
    public int getJogo() {
        return jogo;
    }

    public void setJogo(int Jogo) {
        this.jogo = Jogo;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
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

    public Jornada getJornada() {
        return jornada;
    }

    public Pessoa getArbitro() {
        return arbitro;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public void setArbitro(Pessoa arbitro) {
        this.arbitro = arbitro;
    }
  
    @Override
    public String toString() {
        return "Jogo{" + "Jogo=" + jogo + ", EquipaCasa=" + EquipaCasa +
                ", EquipaFora=" + EquipaFora + ", data=" + data + ", Estadio="
                + Estadio + ", listEvento=" + listEvento + '}';
    }

    public Object getLiga() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String retornaStringJogoVersusFormatada(){
    
        return String.format("%s x %s", this.getEquipaCasa().getNome(),this.getEquipaFora().getNome());
    }
    
    public String retornaStringJogoResultadoFormatada(){
    
        return String.format("%d - %d", this.getGolosCasa(),this.getGolosFora());
    }
}
