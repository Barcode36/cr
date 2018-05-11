/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapports;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Lucille
 */
public class MenuController implements Initializable
{
    //Récupération des id des contrôleurs de la vue
    @FXML private Button reports;
    @FXML private Button visitors;
    @FXML private Button doctors;
    @FXML private Button medicine;
    @FXML private Button quit;
    
    
    //Getsion du clic en fonction de la source de l'id
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException
    {
        if(event.getSource()==reports)
        { //Redirection sur la liste des rapports
            redirect("Report");
        }
        else if(event.getSource()==visitors)
        { //Redirection sur les visiteurs
            redirect("Visitors");
        }
        else if(event.getSource()==doctors)
        { //Redirection sur les praticiens
            redirect("Doctors");
        }
        else if(event.getSource()==medicine)
        { //Redirection sur les médicaments
            redirect("Medicine");
        }
        else if(event.getSource()==quit)
        {
            System.exit(0);
        }
    }
    
   //Méthode de redirection
    public void redirect(String view) throws IOException
    {
        Stage stage = (Stage)reports.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view/"+view+"View.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Pas besoin d'action particulière
    } 
    
}
