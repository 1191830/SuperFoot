/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import com.mycompany.superfute.models.EquipaTecnica;
import com.mycompany.superfute.models.Estadio;
import com.mycompany.superfute.models.Jogador;
import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class Equipa {

    private int id;
    private String nome;
    private Estadio estadio;
    private ArrayList<Jogador> jogadores;
    private ArrayList<EquipaTecnica> funcionarios;
    

    public Equipa() {

    }

    public Equipa(int id, String nome, Estadio estadio) {
        this.id = id;
        this.nome = nome;
        this.estadio = estadio;
    }
    
    public Equipa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public ArrayList<EquipaTecnica> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<EquipaTecnica> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    
 @Override
    public String toString() {
        return "Tean{" + "id=" + id + ", nome=" + nome + ", estádio=" + estadio + '}';
    }
}
