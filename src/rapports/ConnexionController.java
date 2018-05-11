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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import rapports.model.ConnexionModel;
import rapports.model.Model;
/**
 *
 * @author Lucille
 */
public class ConnexionController implements Initializable
{
    //Récupération des contrôles de la vue
    @FXML private TextField user;
    @FXML private PasswordField password;
    @FXML private Button submit;
    @FXML private Button quit;
    @FXML private Label error;
    private Model model;
    private ConnexionModel connexionModel;
       
    //Gestion du clic
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException
    { //En fonction de la source du clic, grâce à l'id du bouton, on gère l'action
        if (event.getSource()==submit)
        {
            verify();
        }
        else if (event.getSource()==quit)
        {
            System.exit(0);
        }
    }

    //Gestion du clavier, pour qu'on puisse se connecter en appuyant sur Enter
    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException
    {
        if(event.getCode()==KeyCode.ENTER)
        {
            verify();
        }
            
    }
    
    //Méthode de vérification des infos
    public void verify() throws IOException
    {
        if(!"".equals(user.getText()) && !"".equals(password.getText()))
        {
            model = Model.getInstance();
            connexionModel = model.getConnexionModel();
            if(connexionModel.connect(user.getText(), password.getText()))
            {
                model.setUser(connexionModel.getUser());
                model.setPasswd(connexionModel.getPasswd());
                model.setMatricule(connexionModel.getMatricule());
                model.setConnection();
                Stage stage = (Stage) submit.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("view/MenuView.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                error.setText("Identifiant et/ou mot de passe incorrect(s)");
                password.clear();
                password.requestFocus();
            }
        }
        else
        {
            error.setText("Merci de remplir tous les champs");
        }
    }
    //Méthode qui initialise le contrôleur
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    } 

}
