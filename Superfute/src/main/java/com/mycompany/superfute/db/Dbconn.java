/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author user
 */

public class Dbconn {
    

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    
        public static Connection getConn() throws SQLException {
            
            
          String connectionUrl =
                "jdbc:sqlserver://ctespbd.dei.isep.ipp.pt:1433;"
                        + "database=DIAS_Grupo2_2021;"
                        + "user=DIAS_grupo2_2021;"
                        + "password=Dias123;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";
          
          Connection conn = null;
        
        try {
            
           conn = DriverManager.getConnection(connectionUrl);
           
           
           
        } 
        catch (SQLException ex)           {
            System.err.println(ex.getMessage());
        }
        
       
            
        return conn; 
    }   
       
            
            
           
        
        
        
        
        
        
    
}   


               
  
    
    
    




    
