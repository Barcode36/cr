/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapports;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rapports.model.Model;
import rapports.model.NewReportModel;

/**
 *
 * @author Lucille
 */
public class NewReportController implements Initializable
{ //Récupération des id des contrôles
    @FXML private Button cancel;
    @FXML private Button quit;
    @FXML private Button search;
    @FXML private Button validate;
    @FXML private TextField num;
    @FXML private TextField praticien;
    @FXML private DatePicker date;
    @FXML private ComboBox motive;
    @FXML private ComboBox med1;
    @FXML private ComboBox med2;
    @FXML private TextArea bilan;
    private Model model;
    private NewReportModel newReportModel;
    
    //Gestion du clic en fonction de l'id source
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException
    {
        if(event.getSource()==cancel)
        { //Retour sur la liste des rapports en gardant les infos saisies en mémoire
            if(!"".equals(bilan.getText())){
                newReportModel.setBilan(bilan.getText());
            }
            if(date.getValue()!=null){
                newReportModel.setDate(date.getValue().toString());
            }
            Stage stage = (Stage)cancel.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/ReportView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==quit)
        {
            System.exit(0);
        }
        else if(event.getSource()==search)
        { //Redirection sur la page des praticiens pour en ajouter un au rapport
            Stage stage = (Stage)search.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/DoctorsView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==validate)
        { //Envoi du rapport
            newReportModel.setNumPra();
            newReportModel.setDate(date.getValue().toString());
            newReportModel.setBilan(bilan.getText());
            newReportModel.insert();
        }
    }
    
    //Gestion des comboBox et enregistrement des sélections
    @FXML
    public void handleSelectionAction(ActionEvent event)
    {
        if(event.getSource()==med1)
        { //Sélection du premier médicament
            newReportModel.setMed1(med1.getSelectionModel().getSelectedItem().toString());
            newReportModel.setNumMed1();
        }
        else if(event.getSource()==med2)
        { //Sélection du deuxième médicament
            newReportModel.setMed2(med2.getSelectionModel().getSelectedItem().toString());
            newReportModel.setNumMed2();
        }
        else if(event.getSource()==motive)
        { //Sélection du motif
            newReportModel.setMotive(motive.getSelectionModel().getSelectedItem().toString());
            newReportModel.setNumMotive();
        }
    }
    
    //Initialisation du contrôleur
    @Override 
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
        newReportModel = model.getNewReportModel();
        //Affectation des listes adéquates aux combobox
        motive.setItems(newReportModel.resultMotives());
        med1.setItems(newReportModel.resultMeds());
        med2.setItems(newReportModel.resultMeds());
        
        //Récupération du prochain numéro de rapport
        num.setText(Integer.toString(newReportModel.setNum()));
        
        //Affectation des valeurs aux champs
        praticien.setText(newReportModel.getPraticien());
        bilan.setText(newReportModel.getBilan());
        
        med1.getSelectionModel().select(newReportModel.getMed1());
        med2.getSelectionModel().select(newReportModel.getMed2());
        motive.getSelectionModel().select(newReportModel.getMotive());
        if(newReportModel.getDate()!=null)
        {
            date.setValue(LocalDate.parse(newReportModel.getDate(),DateTimeFormatter.ISO_DATE));
        }
    } 
}
