/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import com.mycompany.superfute.models.Estadio;

/**
 *
 * @author bruno
 */
public class Equipa {

    private int id;
    private String nome;
    private Estadio estadio;

    public Equipa() {

    }

    public Equipa(int id, String nome, Estadio estadio) {
        this.id = id;
        this.nome = nome;
        this.estadio = estadio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public Estadio getestadio() {
        return estadio;
    }

    public void setestadio(Estadio estadio) {
        this.estadio = estadio;
    }
 @Override
    public String toString() {
        return "Tean{" + "id=" + id + ", nome=" + nome + ", estádio=" + estadio + '}';
    }
}
