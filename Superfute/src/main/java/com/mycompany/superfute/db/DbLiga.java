/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Liga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bruno
 */
public class DbLiga {

    /**
     *
     * @return
     */
    public static ObservableList<Liga> obterLigas() throws SQLException{
    
        ObservableList<Liga> listaLiga = FXCollections.observableArrayList();
        Connection conn = Dbconn.getConn();
        String query = "SELECT * FROM  LIGA";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Liga liga; 
            
            while(rs.next()){
            
                liga = new Liga(rs.getString("nome"),rs.getInt("ano"));
                listaLiga.add(liga);
            }
            
        } catch(Exception ex){       
            ex.printStackTrace();      
        }
        
        return listaLiga;
   
    
    }
    
    
    public static ArrayList<Liga> obterArrayLigas() throws SQLException{
    
       ArrayList<Liga> listaLiga = new ArrayList<Liga>();
        Connection conn = Dbconn.getConn();
        String query = "SELECT * FROM  LIGA";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Liga liga; 
            
            while(rs.next()){
            
                liga = new Liga(rs.getString("nome"),rs.getInt("ano"));
                listaLiga.add(liga);
            }
            
        } catch(Exception ex){       
            ex.printStackTrace();      
        }
        
        return listaLiga;
   
    
    }
    
    public static Liga obterLigaID(int id) throws SQLException{
    
        Liga liga = new Liga();
        Connection conn = Dbconn.getConn();
        String query = "SELECT * FROM  LIGA where ano = " + id;
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
            
                liga = new Liga(rs.getString("nome"),rs.getInt("ano"));

            }
            
        } catch(Exception ex){       
            ex.printStackTrace();      
        }
        
        return liga;
   
    
    }

    public static boolean inserirLigaDB(Liga liga) throws SQLException {

        try {
            Connection con = Dbconn.getConn();
            PreparedStatement pst;
            pst = con.prepareStatement("insert into liga" + "(ano,nome)"
                    + "values(?,?)");
            pst.setInt(1, liga.getAno());
            pst.setString(2, liga.getNome());
            pst.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean alterarLigaDb(Liga liga) throws SQLException {
        
        System.out.println("HELLO");
        try {
            Connection con = Dbconn.getConn();
            PreparedStatement pst;
            pst = con.prepareStatement("Update liga "
                    + "set nome= ? "
                    + "Where ano= ?");
            
            pst.setString(1, liga.getNome());
            pst.setInt(2, liga.getAno());
            pst.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
            return false;
        }
    }

    public static boolean eliminarLigaDb(Liga liga) throws SQLException {
        try {
            Connection con = Dbconn.getConn();
            PreparedStatement pst;
            pst = con.prepareStatement("Delete from Liga"
                    + "Where ano = ?");
            pst.setInt(1, liga.getAno());
            pst.executeQuery();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    
}
