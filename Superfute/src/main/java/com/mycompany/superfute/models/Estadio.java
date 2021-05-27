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

    private String nome;
    private String local;
    private int id;

    public Estadio() {

    }

    public Estadio(String nome, String local, int id) {
        this.nome = nome;
        this.local = local;
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 @Override
    public String toString() {
        return "Estádio{" + "nome=" + nome + ", local=" + local + ", id=" + id + '}';
    }
}
