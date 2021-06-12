/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

/**
 *
 * @author pcoelho
 */
public class Estadio {


    private int id;
    private String nome;
    private int id_cidade;

    public Estadio() {

    }

    public Estadio(int id, String nome, int id_cidade) {
        this.id = id;
        this.nome = nome;
        this.id_cidade = id_cidade;
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

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    @Override
    public String toString() {
        return "Estadio{" + "id=" + id + ", nome=" + nome + ", id_cidade=" + id_cidade + '}';
    }
    
    
    
    
    

} 