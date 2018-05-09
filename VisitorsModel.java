/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapports.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Lucille
 */
public class VisitorsModel {
    private Connection connect;
    private ObservableList<ObservableList<String>> data;
    private ObservableList<String> row;
    private String query;
    
    public VisitorsModel(Connection connect)
    {
        this.connect = connect;
    }
    
    //Retourne une liste avec toutes les data des visiteurs
    public ObservableList result(){
        data = FXCollections.<ObservableList<String>>observableArrayList();
        row = FXCollections.observableArrayList();
        this.query = "SELECT VIS_NOM, VIS_PRENOM, VIS_ADRESSE, VIS_CP, VIS_VILLE, SEC_CODE, LAB_CODE "+
                "FROM visiteur "+
                "LEFT JOIN type_visiteur ON visiteur.TYV_CODE=type_visiteur.TYV_CODE";
        
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            int i=0;
            while (result.next())
            { //Récupération des infos au format souhaité
                String nom = result.getString("VIS_NOM");
                String prenom = result.getString("VIS_PRENOM");
                String adresse = result.getString("VIS_ADRESSE");
                String cp = result.getString("VIS_CP");
                String ville = result.getString("VIS_VILLE");
                String sec = result.getString("SEC_CODE");
                String lab = result.getString("LAB_CODE");
                row.addAll(nom,prenom,adresse+" "+cp+" "+ville,sec,lab);
                data.add(i,row);
                row = FXCollections.observableArrayList();
                i++;
            }     
        } 
        catch(Exception e)
        {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Erreur");
            error.setHeaderText(null);
            error.setContentText("Une erreur a été rencontrée, veuillez réessayer plus tard.");
            error.showAndWait();
        }
        return data;
    }
}
