/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapports;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import rapports.model.Model;
import rapports.model.VisitorsModel;

/**
 *
 * @author Lucille
 */
public class VisitorsController implements Initializable
{ //Récupération des id des contrôles
    @FXML private Button quit;
    @FXML private Button back;
    @FXML private TableView table;
    @FXML private TableColumn<ObservableList<String>, String> name;
    @FXML private TableColumn<ObservableList<String>, String> fname;
    @FXML private TableColumn<ObservableList<String>, String> address;
    @FXML private TableColumn<ObservableList<String>, String> sect;
    @FXML private TableColumn<ObservableList<String>, String> lab;
  //Récupération des data du modèle
    private ObservableList<ObservableList<String>> data;
    private Model model;
    private VisitorsModel visitorsModel;
    
    //Gestion du clic en fonction de l'id source
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException
    {
        if(event.getSource()==quit)
        {
            System.exit(0);
        }
        else if(event.getSource()==back)
        { //Retour au menu principal
            Stage stage = (Stage)back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/MenuView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
  //Initialisation du contrôleur et récupération des data
    @Override 
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
        visitorsModel = model.getVisitorsModel();
        
        //Affectation du type de données aux colonnes, texte, chiffre
        name.setCellValueFactory((CellDataFeatures<ObservableList<String>, String> cdf)->
            new SimpleStringProperty(cdf.getValue().get(0)));
        
        fname.setCellValueFactory((CellDataFeatures<ObservableList<String>, String> cdf)->
            new SimpleStringProperty(cdf.getValue().get(1)));
        
        address.setCellValueFactory((CellDataFeatures<ObservableList<String>, String> cdf)->
            new SimpleStringProperty(cdf.getValue().get(2)));
        
        sect.setCellValueFactory((CellDataFeatures<ObservableList<String>, String> cdf)->
            new SimpleStringProperty(cdf.getValue().get(3)));
        
        lab.setCellValueFactory((CellDataFeatures<ObservableList<String>, String> cdf)->
            new SimpleStringProperty(cdf.getValue().get(4)));
        
        data = visitorsModel.result();
        for(int i=0; i<data.size()-1;i++) //-1 parce que le dernier c'est swiss bourdin et on s'en fout un peu
        { //Distribution des données dans les colonnes
            ObservableList<String> row = FXCollections.observableArrayList(data.get(i));
            table.getItems().add(row);
        }  
    } 
}
