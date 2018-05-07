package rapports.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;

public class ConnexionModel {
    private Connection connect; 
    private String query;
    private String user;
    private String matricule;
    private String url;
    private String passwd;
    
    // Connexion à la base de donnée
    public ConnexionModel(Connection connect, String url, String user, String passwd){
        this.connect = connect;
        this.url = url;
    }  
    
    public boolean connect(String id, String pw){
        query = "SELECT LOWER(TYV_LIBELLE), VIS_MATRICULE FROM type_visiteur " +
                       "INNER JOIN visiteur ON type_visiteur.TYV_CODE=visiteur.TYV_CODE " +
                       "WHERE VIS_NOM=\""+id+"\" AND LOWER(DATE_FORMAT(VIS_DATEEMBAUCHE, '%d-%b-%Y'))='"+pw+"'";
        try (Statement state = connect.createStatement(); ResultSet result = state.executeQuery(query)){
            if(result.next()){
                user=result.getObject(1).toString();
                matricule=result.getObject(2).toString();
                
                switch (user){
                    case "visiteur":
                        user="vaalrhona_visite";
                        passwd="visite";
                        break;
                    case "delegue":
                        user="vaalrhona_deleg";
                        passwd="deleg";
                        break;
                    case "responsable":
                        user="vaalrhona_respo";
                        passwd="respo";
                }
                connect = DriverManager.getConnection(url, user, passwd);
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Erreur");
            error.setHeaderText(null);
            error.setContentText("Une erreur a été rencontrée, veuillez réessayer plus tard.");
            error.showAndWait();
            e.printStackTrace();
            return false;
        }
    }
    
    public String getUser()
    {
        return user;
    }
    
    public String getPasswd()
    {
        return passwd;
    }
    
    public String getMatricule()
    {
        return matricule;
    }
    
}