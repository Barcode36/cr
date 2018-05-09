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
    private int numMotive;
    private String bilan;
    private String query;
    private String med1;
    private String numMed1;
    private String med2;
    private String numMed2;
    private String matricule;
    
    public NewReportModel(Connection connect, String matricule)
    {
        this.connect = connect;
        this.matricule = matricule;
    }
    
  //Retourne une liste avec la liste des motifs de visite
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
    
  //Insertion du rapport dans la DB
    public boolean insert()
    {
        query = "INSERT INTO rapport_visite(VIS_MATRICULE,RAP_NUM,PRA_NUM,RAP_DATE,RAP_BILAN,TYM_CODE,RAP_MOTIF,RAP_MED1,RAP_MED2) VALUES("
                +matricule+","+num+","+numPra+","+date+","+bilan+","+numMotive+",null,"+numMed1+","+numMed2+")";
        try (Statement state = connect.createStatement())
        {
            int result = state.executeUpdate(query);
            if(result>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    //Retourne une liste avec les noms des médicaments
    public ObservableList resultMeds()
    {
        query = "SELECT MED_NOMCOMMERCIAL FROM medicament";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            resultMeds = FXCollections.observableArrayList();
            while(result.next())
            {
                resultMeds.add(result.getString("MED_NOMCOMMERCIAL"));
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
    
   //Retourne le numéro du prochain rapport
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
    
   //Retourne le numéro du praticien correspondant à ses nom et prénom
    public void setNumPra()
    {
        query = "SELECT PRA_NUM FROM praticien WHERE CONCAT(PRA_NOM,' ',PRA_PRENOM)='"+praticien+"'";
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
    
  //Récupère le dépôt légal du med1
    public void setNumMed1()
    {
        query = "SELECT MED_DEPOTLEGAL FROM medicament WHERE MED_NOMCOMMERCIAL='"+med1+"'";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            while(result.next())
            {
                numMed1 = "'"+result.getString("MED_DEPOTLEGAL")+"'";
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
    
    public void setMed2(String med2)
    {
        this.med2 = med2;
    }
    
    public String getMed2()
    {
        return med2;
    }
    
  //Récupère le dépôt légal du med 2
    public void setNumMed2()
    {
        query = "SELECT MED_DEPOTLEGAL FROM medicament WHERE MED_NOMCOMMERCIAL='"+med2+"'";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            while(result.next())
            {
                numMed2 = "'"+result.getString("MED_DEPOTLEGAL")+"'";
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
    public void setBilan(String bilan)
    {
        this.bilan = (bilan!=null)?"'"+bilan+"'":null;
    }
    
    public String getBilan()
    {
        return bilan;
    }
    
    public void setDate(String date)
    {
        this.date = (date!=null)?"'"+date+"'":null;
    }
    
    public String getDate()
    {
        return date;
    }
    
    //Réinitialisation du modèle
    public void clean()
    {
        this.date = null;
        this.bilan = null;
        this.med1 = null;
        this.numMed1 = null;
        this.med2 = null;
        this.numMed2 = null;
        this.motive = null;
        this.praticien = null;
    }
  //Récupère le numéro du motif de visite
    public void setNumMotive()
    {
        query = "SELECT TYM_CODE FROM type_motif WHERE TYM_LIBELLE='"+motive+"'";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query))
        {
            while(result.next())
            {
                numMotive = result.getInt("TYM_CODE");
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
}
