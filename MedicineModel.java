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
public class MedicineModel {
    private Connection connect;
    private ObservableList<ObservableList<String>> data;
    private ObservableList<String> row;
    private String query;
    
    public MedicineModel(Connection connect)
    {
        this.connect = connect;
    }
    
  //Retourne une liste avec toutes les infos des médicaments
    public ObservableList result(){
        data = FXCollections.<ObservableList<String>>observableArrayList();
        row = FXCollections.observableArrayList();
        this.query = "SELECT MED_DEPOTLEGAL, MED_NOMCOMMERCIAL, FAM_LIBELLE, MED_COMPOSITION, MED_EFFET, MED_CONTREINDIC, MED_PRIXECHANTILLON "+
                     "FROM medicament LEFT JOIN famille ON medicament.FAM_CODE=famille.FAM_CODE";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query)){
            int i=0;
            while (result.next())
            { //On récupère les infos dans le format souhaité
                String depot = result.getString("MED_DEPOTLEGAL");
                String nomCom = result.getString("MED_NOMCOMMERCIAL");
                String fam = result.getString("FAM_LIBELLE");
                String comp = result.getString("MED_COMPOSITION");
                String effet = result.getString("MED_EFFET");
                String contre = result.getString("MED_CONTREINDIC");
                String prix = "N/A";
                row.addAll(depot,nomCom,fam,comp,effet,contre,prix);
                data.add(i,row);
                row = FXCollections.observableArrayList();
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Erreur");
            error.setHeaderText("Erreur");
            error.setContentText("Une erreur a été rencontrée, veuillez réessayer plus tard.");
            error.showAndWait();
        }
        return data;
    }    
}
