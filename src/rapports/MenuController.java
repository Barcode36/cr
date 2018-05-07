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
    @FXML private Button reports;
    @FXML private Button visitors;
    @FXML private Button doctors;
    @FXML private Button medicine;
    @FXML private Button quit;
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException
    {
        if(event.getSource()==reports)
        {
            Stage stage = (Stage)reports.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/ReportView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==visitors)
        {
            Stage stage = (Stage)visitors.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/VisitorsView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==doctors)
        {
            Stage stage = (Stage)doctors.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/DoctorsView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==medicine)
        {
            Stage stage = (Stage)medicine.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/MedicineView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==quit)
        {
            System.exit(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    } 
    
}
