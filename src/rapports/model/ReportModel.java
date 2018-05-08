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
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Lucille
 */
public class ReportModel {
    private Connection connect;
    private String query;
    private ObservableList<ObservableList> data;
    private ObservableList row;
    
    public ReportModel(Connection connect)
    {
        this.connect = connect;
    }
    public ObservableList result()
    {
        data = FXCollections.<ObservableList>observableArrayList();
        row = FXCollections.observableArrayList();
        this.query = "SELECT RAP_NUM, PRA_NOM, PRA_PRENOM, RAP_DATE, TYM_LIBELLE, RAP_BILAN, RAP_MED1, RAP_MED2 "+
                "FROM rapport_visite "+
                "INNER JOIN type_motif ON rapport_visite.TYM_CODE = type_motif.TYM_CODE "+
                "INNER JOIN praticien ON rapport_visite.PRA_NUM = praticien.PRA_NUM "+
                "ORDER BY RAP_NUM";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            int i=0;
            while (result.next())
            {
                String num = result.getString("RAP_NUM");
                String nom = result.getString("PRA_NOM");
                String prenom = result.getString("PRA_PRENOM");
                String date = result.getString("RAP_DATE");
                String libelle = result.getString("TYM_LIBELLE");
                String bilan = result.getString("RAP_BILAN");
                String med1 = result.getString("RAP_MED1");
                String med2 = result.getString("RAP_MED2");
                row.addAll(num,nom+" "+prenom,date,libelle,bilan,med1,med2);
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
