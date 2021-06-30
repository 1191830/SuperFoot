/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbArbitro;
import com.mycompany.superfute.db.DbEquipa;
import com.mycompany.superfute.db.DbEstadio;
import com.mycompany.superfute.db.DbJogador;
import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Estadio;
import com.mycompany.superfute.models.Evento;
import com.mycompany.superfute.models.Jogador;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class JogoFormController implements Initializable {

    @FXML
    private Label labelLigaAno;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<Equipa> selecionarEquipaCasa;
    @FXML
    private ComboBox<Equipa> SelecionarEquipaVisitante;
    @FXML
    private DatePicker selecionarData;
    @FXML
    private ComboBox<String> selecionarHora;
    @FXML
    private ComboBox<String> selecionarMinuto;
    @FXML
    private ComboBox<Estadio> selecionarEstadio;
    @FXML
    private ComboBox<Pessoa> selecionarArbitro;
    @FXML
    private TableView<?> tabelaFormacao;
    @FXML
    private TableColumn<?, ?> colunaEquipaCasa;
    @FXML
    private TableColumn<?, ?> colunaTitularCasa;
    @FXML
    private TableColumn<?, ?> colunaSuplentesCasa;
    @FXML
    private TableColumn<?, ?> colunaEquipaVisitante;
    @FXML
    private TableColumn<?, ?> colunaTitularVisitante;
    @FXML
    private TableColumn<?, ?> colunaSuplentesVisitante;
    @FXML
    private ScrollPane tabelaEventos;
    @FXML
    private TableColumn<?, ?> colunaEvento;
    @FXML
    private TableColumn<?, ?> colunaPessoa;
    @FXML
    private TableColumn<?, ?> colunaEquipa;
    @FXML
    private TableColumn<?, ?> colunaMinuto;
    @FXML
    private TableColumn<?, ?> colunaParte;
    @FXML
    private Button btnAdicionarFormação;
    @FXML
    private Button btnEditarFormação;
    @FXML
    private Button btnRemoverFormação;
    @FXML
    private Button btnAdicionarEvento;
    @FXML
    private Button btnEditarEvento;
    @FXML
    private Button btnRemoverEvento;
    @FXML
    private RadioButton radioEquipaCasa;
    @FXML
    private ToggleGroup equipaFormacao;
    @FXML
    private RadioButton radioEquipaVisitante;

    private Jogo jogo;
    private Stage stageDialog;
    private boolean btnClicked;
    private ArrayList<Estadio> estadios;
    private ArrayList<Pessoa> arbitros;
    private ArrayList<Equipa> equipas;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Evento> eventos;
    private ArrayList<String> horas;
    private ArrayList<String> minutos;

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
        definirValorCamposObjeto();

    }

    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage stageDialog) {
        this.stageDialog = stageDialog;
    }

    public boolean isBtnClicked() {
        return btnClicked;
    }

    public void setBtnClicked(boolean btnClicked) {
        this.btnClicked = btnClicked;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            preencherArrayList();
            inserirCampos();
            validaArrayListEquipa(selecionarEquipaCasa, true);
            validaArrayListEquipa(SelecionarEquipaVisitante, false);
        } catch (SQLException ex) {
            Logger.getLogger(JogoFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void validaArrayListEquipa(ComboBox equipa, boolean casaFora) {
        equipa.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    
                    if (newValue != null) {
                        System.out.println("AQUIdentro");
                        if (casaFora) {
                            if (newValue.equals(SelecionarEquipaVisitante.getSelectionModel().getSelectedItem())) {
                                try {
                                    selecionarEquipaCasa.getSelectionModel().clearSelection();
                                } catch (Exception e) {
                                    MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Não podes escolher equipas iguais", "Inválido");
                                }

                            }
                        } else {
                            if (newValue.equals(selecionarEquipaCasa.getSelectionModel().getSelectedItem())) {
                                try {
                                    SelecionarEquipaVisitante.getSelectionModel().clearSelection();
                                } catch (Exception e) {
                                    MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Não podes escolher equipas iguais", "Inválido");

                                }

                            }
                        }

                    }
                    System.out.println(newValue);
                }
        );
    }

    public void horasMinutos() {
        horas = new ArrayList<>();
        minutos = new ArrayList<>();
        for (int i = 0; i < 24; i++) {

            horas.add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            minutos.add(String.format("%02d", i));
        }
    }

    public void preencherArrayList() throws SQLException {
        equipas = DbEquipa.getEquipasObject();
        arbitros = DbArbitro.obterArbitros();
        //  jogadores =  DbJogador.obterJogadorNomePorEquipa();
        estadios = DbEstadio.obterEstadios();
        horasMinutos();
    }

    public void inserirCampos() {
        selecionarEquipaCasa.getItems().addAll(equipas);
        SelecionarEquipaVisitante.getItems().addAll(equipas);
        selecionarArbitro.getItems().addAll(arbitros);
        selecionarEstadio.getItems().addAll(estadios);
        selecionarHora.getItems().addAll(horas);
        selecionarMinuto.getItems().addAll(minutos);

    }

    public void definirValorCamposObjeto() {
        if (jogo != null) {
            selecionarEquipaCasa.setValue(jogo.getEquipaCasa());
            SelecionarEquipaVisitante.setValue(jogo.getEquipaFora());
            selecionarArbitro.setValue(jogo.getArbitro());
            selecionarEstadio.setValue(jogo.getEstadio());

        }
    }

    public void setDadosJogo() {
        if (validarCampos()) {
            jogo.setEquipaCasa(selecionarEquipaCasa.getSelectionModel().getSelectedItem());
            jogo.setEquipaFora(SelecionarEquipaVisitante.getSelectionModel().getSelectedItem());
            jogo.setArbitro(selecionarArbitro.getSelectionModel().getSelectedItem());
            jogo.setData(selecionarData.getValue());
        }
    }

    public boolean validarCampos() {

        if (selecionarEquipaCasa.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar Equipa Casa", "Selecionar");
            return false;
        }
        if (SelecionarEquipaVisitante.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar Equipa Visitante", "Selecionar");
            return false;
        }
        if (selecionarArbitro.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar Arbitro", "Selecionar");
            return false;
        }
        if (selecionarEstadio.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar Estádio", "Selecionar");
            return false;
        }
        if (selecionarData.getValue() == null) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar data", "Selecionar");
            return false;
        }
        if (selecionarHora.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar hora", "Selecionar");
            return false;
        }
        if (selecionarMinuto.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar minuto", "Selecionar");
            return false;
        }
        return true;
    }

    @FXML
    private void btnAplicar(ActionEvent event) {
        setDadosJogo();
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    @FXML
    private void btnAdicionarFormação(ActionEvent event) {
    }

    @FXML
    private void btnEditarFormação(ActionEvent event) {
    }

    @FXML
    private void btnRemoverFormação(ActionEvent event) {
    }

    @FXML
    private void btnAdicionarEvento(ActionEvent event) {
    }

    @FXML
    private void btnEditarEvento(ActionEvent event) {
    }

    public void inserirNacionalidade() throws SQLException {
        for (Estadio e : estadios) {
            //  selecionarNacionalidade.getItems().addAll(p.getNome());
        }
    }

}
