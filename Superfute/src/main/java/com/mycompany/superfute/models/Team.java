/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import com.mycompany.superfute.models.Stadium;

/**
 *
 * @author bruno
 */
public class Team {

    private int id;
    private String name;
    private Stadium stadium;

    public Team() {

    }

    public Team(int id, String nome, Stadium stadium) {
        this.id = id;
        this.name = name;
        this.stadium = stadium;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

}
