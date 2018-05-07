/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapports.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


/**
 *
 * @author Lucille
 */
public class DoctorsModel {
    private ObservableList<ObservableList> data;
    private ObservableList row;
    private String query;
    private Connection connect;
    
    public DoctorsModel(Connection connect)
    {
        this.connect = connect;
    }
    public ObservableList result(){
        data = FXCollections.<ObservableList>observableArrayList();
        row = FXCollections.observableArrayList();
        this.query = "SELECT PRA_NOM, PRA_PRENOM, PRA_ADRESSE, PRA_CP, PRA_VILLE, PRA_COEFNOTORIETE, TYP_LIEU "+
                     "FROM praticien "+
                     "LEFT JOIN type_praticien ON praticien.TYP_CODE=type_praticien.TYP_CODE";
            
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query)){
            int i=0;
            while (result.next()){
                String nom = result.getString("PRA_NOM");
                String prenom = result.getString("PRA_PRENOM");
                String adresse = result.getString("PRA_ADRESSE");
                String cp = result.getString("PRA_CP");
                String ville = result.getString("PRA_VILLE");
                float coeff = result.getFloat("PRA_COEFNOTORIETE");
                String lieu = result.getString("TYP_LIEU");
                row.addAll(nom,prenom,adresse+" "+cp+" "+ville,coeff,lieu);
                data.add(i,row);
                row=FXCollections.observableArrayList();
                i++;
            }
        }
        catch(Exception e){
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