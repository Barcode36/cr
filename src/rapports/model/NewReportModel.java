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
public class NewReportModel {
    private Connection connect;
    private int num;
    private String praticien;
    private int numPra;
    private String date;
    private ObservableList<String> resultMotives;
    private ObservableList<String> resultMeds;
    private String motive;
    private String bilan;
    private String query;
    private String med1;
    private String med2;
    
    public NewReportModel(Connection connect)
    {
        this.connect = connect;
    }
    
    public ObservableList resultMotives()
    {
        query = "SELECT TYM_LIBELLE FROM type_motif";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            resultMotives = FXCollections.observableArrayList();
            while(result.next())
            {
                resultMotives.add(result.getString("TYM_LIBELLE"));
            }
        }
        catch (Exception e){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Erreur");
            error.setHeaderText(null);
            error.setContentText("Une erreur a été rencontrée, veuillez réessayer plus tard.");
            error.showAndWait();
            e.printStackTrace();
        }
        return resultMotives;
    }
    
    public ObservableList resultMeds()
    {
        query = "SELECT MED_DEPOTLEGAL FROM medicament";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            resultMeds = FXCollections.observableArrayList();
            while(result.next())
            {
                resultMeds.add(result.getString("MED_DEPOTLEGAL"));
            }
        }
        catch (Exception e){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Erreur");
            error.setHeaderText(null);
            error.setContentText("Une erreur a été rencontrée, veuillez réessayer plus tard.");
            error.showAndWait();
            e.printStackTrace();
        }
        return resultMeds;
    }
    
    public void setMotive(String motive)
    {
        this.motive = motive;
    }
    
    public String getMotive()
    {
        return motive;
    }
    
    public int setNum()
    {
        query = "SELECT MAX(RAP_NUM) FROM rapport_visite";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            while(result.next())
            {
                num = result.getInt("MAX(RAP_NUM)")+1;
            }
        }
        catch (Exception e){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Erreur");
            error.setHeaderText(null);
            error.setContentText("Une erreur a été rencontrée, veuillez réessayer plus tard.");
            error.showAndWait();
            e.printStackTrace();
        }
        return num;
    }
    
    public void setPraticien(String praticien)
    {
        this.praticien = praticien;
    }
    
    public String getPraticien()
    {
        return this.praticien;
    }
    
    public void setNumPra()
    {
        query = "SELECT PRA_NUM FROM rapport_visite WHERE CONCAT(PRA_NOM,' ',PRA_PRENOM)='"+praticien+"'";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            while(result.next())
            {
                numPra = result.getInt("PRA_NUM");
            }
        }
        catch (Exception e){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Erreur");
            error.setHeaderText(null);
            error.setContentText("Une erreur a été rencontrée, veuillez réessayer plus tard.");
            error.showAndWait();
            e.printStackTrace();
        }
    }
    
    public void setMed1(String med1)
    {
        this.med1 = med1;
    }
    
    public String getMed1()
    {
        return med1;
    }
    
    public void setMed2(String med2)
    {
        this.med2 = med2;
    }
    
    public String getMed2()
    {
        return med2;
    }
    
    public void setBilan(String bilan)
    {
        this.bilan = bilan;
    }
    
    public String getBilan()
    {
        return bilan;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String getDate()
    {
        return date;
    }
}
