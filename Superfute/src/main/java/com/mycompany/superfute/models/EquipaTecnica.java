/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.models;

import java.time.LocalDate;

/**
 *
 * @author nelso
 */
public class EquipaTecnica extends Pessoa {
    
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int idFuncao;
    private String funcao;

    public EquipaTecnica() {
    }

    public EquipaTecnica(LocalDate dataInicio, LocalDate dataFim, 
            int idFuncao, String funcao, int id, String nome, String nacionalidade) {
        super(id, nome, nacionalidade);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idFuncao = idFuncao;
        this.funcao = funcao;
    }
    
    
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public int getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "EquipaTecnica{" + ""
                + " dataInicio=" + dataInicio + ","
                + " dataFim=" + dataFim + ","
                + " idFuncao=" + idFuncao + ","
                + " funcao=" + funcao + '}';
    }
    
    
    
}
