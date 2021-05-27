/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

/**
 *
 * @author bruno
 */
public class Event {

    private int minute;
    private int idGame;
    private int idTeam;
    private int idPlayer;
    private int typeEvent;

    public Event() {

    }

    public Event(int minute, int idGame, int idTeam, int idPlayer, int typeEvent) {
        this.minute = minute;
        this.idGame = idGame;
        this.idTeam = idTeam;
        this.idPlayer = idPlayer;
        this.typeEvent = typeEvent;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(int typeEvent) {
        this.typeEvent = typeEvent;
    }
 @Override
    public String toString() {
        return "Evento{" + "minuto=" + minute + ", id jogo=" + idGame + ", id equipa=" + idTeam + ", id jogador =" + idPlayer + ",tipo de evento"  + typeEvent + '}';
    }
}
