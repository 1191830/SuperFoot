/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import static com.mycompany.superfute.db.DbEstadio.ShowMessage;
import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Jornada;
import com.mycompany.superfute.models.Pessoa;
import com.mycompany.superfute.models.Jogador;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author bruno
 */
public class DbJogo {
     public static ArrayList<Jogo> jogoDB() throws SQLException {
        ArrayList<Jogo> jogo = new ArrayList<>();

        try {
            Connection con = Dbconn.getConn();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("Select * from liga");
            while (rst.next()) {
                Jogo j = new Jogo();
//                j.getJogo().setJogo(rst.getInt("idJogo"));
                jogo.add(j);
            }
            return jogo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
     
     public static Jogo getJogoById(int id) throws SQLException{
        Jogo jogo = new Jogo();
         Date dia;
        
        Connection conn = Dbconn.getConn();
        String cmd = "";

            try {
                cmd = "select * from jogo where id = " + id;

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(cmd);

                while (rs.next()) {
 
                   jogo = new Jogo(
                    id,
                    DbJornada.getJornadaByID(rs.getInt("jornada")),
                    DbEstadio.getEstadiosByID(rs.getInt("idEstadio")), 
                    DbArbitro.getArbitroByID(rs.getInt("arbitro"))
                   );
                }
                jogo.setEquipaCasa(DbEquipa.getEquipaJogo(jogo, 0));
                jogo.setEquipaFora(DbEquipa.getEquipaJogo(jogo, 1));
                
                            
                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
            return jogo;
        }
     
     public static int getLastId(){
         int id= 0;
        try {
            Connection con = Dbconn.getConn();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("select TOP 1 id from jogo order by id desc");
            while (rst.next()) {
                id = rst.getInt("id");
            }
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
     }
     
    public static ArrayList<Pessoa> getPessoaJogo(Jogo jogo, Equipa equipa, int funcao) throws SQLException {

            ArrayList<Pessoa> arrPessoa = new ArrayList();; 
            Connection conn = Dbconn.getConn();
            String cmd = "";
            int id;

            try {
                cmd = "select * from pessoaJogo where idJogo = " + jogo.getJogo() + " and idEquipa = " 
                        + equipa.getId() + " and funcaoJogo = " + funcao;

                Statement st = conn.createStatement();

                ResultSet rs = st.executeQuery(cmd);

                while (rs.next()) {
                    Pessoa pessoa = new Pessoa(
                    );
                    
                    pessoa.setNome(DbPessoa.getNomebyId(rs.getInt("idPessoa")));
                    arrPessoa.add(pessoa);
                }

                st.close();
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
            return arrPessoa;
        }
     
     public static ArrayList<Jogo> obterJogosLigaJornada(Jornada jornada) throws SQLException {
       
        ArrayList<Jogo> arrJogos = new ArrayList();; 
        Connection conn = Dbconn.getConn();
        String cmd = "";
        int id;

        try {
            cmd = "Select Distinct * from func_resultadoTodos(-1) where jornada = " + jornada.getIdJornada();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Jogo jogo = new Jogo(
                        rs.getInt("jogo"),
                        rs.getNString("equipaCasa"),                       
                        rs.getInt("golosCasa"),
                        rs.getNString("equipaFora"),                       
                        rs.getInt("golosFora"));
            
            arrJogos.add(jogo);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return arrJogos;
    }
     
     public static Jogo getResultado(Jogo jogo) throws SQLException {      
        
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            cmd = "Select Distinct * from func_resultadoTodos(" + jogo.getJogo()+ ")";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                jogo = new Jogo(
                        rs.getInt("jogo"),
                        rs.getNString("equipaCasa"),                       
                        rs.getInt("golosCasa"),
                        rs.getNString("equipaFora"),                       
                        rs.getInt("golosFora"));
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return jogo;
    }
     
    public static String getResultadoIntervalo(Jogo jogo) throws SQLException {
        
        
        String resultado = "";
        int id;

        try {
            Connection conn = Dbconn.getConn();
            String cmd = "select * from func_golosIntervalo(" + jogo.getJogo() + ")";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                resultado = rs.getString("resultado1");
                resultado = resultado + " x ";
                resultado = resultado + rs.getString("resultado2");              
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return resultado;
    }
     
    public static ArrayList<Jogo> getResultadosJogosEquipa(int idEquipa) throws SQLException {
        ArrayList<Jogo> jogos =  new ArrayList<>();
        try {
            Connection conn = Dbconn.getConn();
             PreparedStatement statement = conn.
                     prepareStatement("select Distinct * from func_resultadoTodos(-1) "
                             + "where idEquipa1 = ? or idEquipa2 = ?");
              statement.setInt(1, idEquipa);
              statement.setInt(2, idEquipa);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Jogo jogo = new Jogo();
                jogo.setEquipaCasa(new Equipa());
                jogo.getEquipaCasa().setNome(rs.getString("equipaCasa"));
                jogo.setEquipaFora(new Equipa());
                jogo.getEquipaFora().setNome(rs.getString("equipaFora"));
                jogo.setGolosCasa(rs.getInt("golosCasa"));
                jogo.setGolosFora(rs.getInt("golosFora"));
                jogo.setJornada(new Jornada());
                jogo.getJornada().setIdJornada(rs.getInt("jornada"));
                jogo.getJornada().setIdLiga(rs.getInt("liga"));
                jogos.add(jogo);
            }
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return jogos;
    }
    
        public static void insertJogo(Jogo jogo) throws SQLException {
        
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            
            //Novo Jogo
            cmd = "INSERT INTO jogo(liga, jornada, idEstadio, arbitro, dia) VALUES (?, ?, ?, ?, getDate())";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, jogo.getJornada().getIdLiga());
            statement.setInt(2, jogo.getJornada().getIdJornada());
            statement.setInt(3, jogo.getEstadio().getId());
            statement.setInt(4, jogo.getArbitro().getId());

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }
        
        public static void deleteJogo(Jogo jogo) throws SQLException {
        
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            
            //Novo Jogo
            cmd = "Delete from jogo where id = ?";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, jogo.getJogo());

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }
        
        public static void deleteJogoEquipa(Jogo jogo) throws SQLException {
        
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            
            //Novo Jogo
            cmd = "Delete from jogoEquipa where idJogo = ?";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, jogo.getJogo());

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }
        
    public static void insertJogoEquipa(Jogo jogo, int casaFora) throws SQLException {
        
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            
            //Novo Jogo
            cmd = "INSERT INTO jogoEquipa(idJogo, idEquipa, casaFora) VALUES (?, ?, " + casaFora + ")";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, jogo.getJogo());
            if(casaFora == 0){
                statement.setInt(2, jogo.getEquipaCasa().getId());
            }else{
                statement.setInt(2, jogo.getEquipaFora().getId());
            }          

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }
    
        public static void insertPessoaJogo(Jogo jogo, Jogador jogador, int funcao, int estado, int casaFora) throws SQLException {
        
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            
            //Novo Jogo
            cmd = "INSERT INTO pessoaJogo(idPessoa, idEquipa, idJogo, funcaoJogo, estadoEmJogo) VALUES (?, ?, ?, " + funcao + ", " + estado + ")";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, jogador.getId());
            if (casaFora == 0){
                statement.setInt(2, jogo.getEquipaCasa().getId());
            }else{
                statement.setInt(2, jogo.getEquipaFora().getId());
            }
            statement.setInt(3, jogo.getJogo());      

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }
    
    public static void updatePessoaJogo(Jogo jogo, Jogador jogador) throws SQLException {
        int funcao = -1;
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            
            //Novo Jogo
            cmd = "Select funcaoJogo from pessoaJogo where idPessoa = ? and idJogo = ?";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, jogador.getId());
            statement.setInt(2, jogo.getJogo());
                      

            //Execute the update
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                funcao = rs.getInt("funcaoJogo");                
             }
            if (funcao == 1 ){
                cmd = "Update pessoaJogo set funcaoJogo = 2 where idPessoa = ? and idJogo = ?";
            }else{
                cmd = "Update pessoaJogo set funcaoJogo = 1 where idPessoa = ? and idJogo = ?";
            }
            statement = conn.prepareStatement(cmd);
            statement.setInt(1, jogador.getId());
            statement.setInt(2, jogo.getJogo());
                      

            //Execute the update
            statement.executeUpdate();
            
            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }
    
    
    
    public static void deletePessoaJogo(Jogo jogo, Jogador jogador) throws SQLException {
        
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            
            //Novo Jogo
            cmd = "Delete from pessoaJogo where idPessoa = ? and idJogo = ?";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, jogador.getId());
            statement.setInt(2, jogo.getJogo());
                      

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }
}
