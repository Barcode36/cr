/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapports;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rapports.model.MedicineModel;
import rapports.model.Model;

/**
 *
 * @author Lucille
 */
public class MedicineController implements Initializable
{ //Récupération des contrôles de la vue
    @FXML private Button back;
    @FXML private Button quit;
    @FXML private MenuItem med;
    @FXML private MenuItem remp1;
    @FXML private MenuItem remp2;
    @FXML private ListView<String> list;
    @FXML private TextField code;
    @FXML private TextArea fam;
    @FXML private TextArea effet;
    @FXML private TextArea contre;
    @FXML private TextArea compo;
  //Récupération des data du modèle
    private ObservableList<ObservableList<String>> data;
    private Model model;
    private MedicineModel medicineModel;
    
  //Gestion du clic en fonction de l'id source
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException
    {
        if(event.getSource()==back)
        { //Redirection au menu principal
            Stage stage = (Stage)back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/MenuView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==quit)
        {
            System.exit(0);
        }
        else if(event.getSource()==med)
        { //Clic droit sur un médicament de la liste, ajout au nouveau rapport
            if(model.getNewReportModel().getMed1()==null)
            {
                model.getNewReportModel().setMed1(
                list.getSelectionModel().getSelectedItem());
                remp1.setText(remp1.getText()+" "+model.getNewReportModel().getMed1());
                remp1.setVisible(true); //On affiche la possibilité de modifier le médicament ajouté
                
            }
            else if(model.getNewReportModel().getMed2()==null)
            { //Max 2 médicaments, on peut seulement modifier le med1 et le med2 sans en ajouter
                model.getNewReportModel().setMed2(
                list.getSelectionModel().getSelectedItem());
                remp2.setText(remp2.getText()+" "+model.getNewReportModel().getMed2());
                remp2.setVisible(true);
                med.setVisible(false);
            }
        }
        else if(event.getSource()==remp1)
        { //Modification du med1
            model.getNewReportModel().setMed1(
            list.getSelectionModel().getSelectedItem());
            remp1.setText("Remplacer "+model.getNewReportModel().getMed1());
        }
        else if(event.getSource()==remp2)
        { //Modification du med2
            model.getNewReportModel().setMed2(
            list.getSelectionModel().getSelectedItem());
            remp2.setText("Remplacer "+model.getNewReportModel().getMed2());
        }
    }
    
    //Gestion du remplissage des champs en fonction du clic souris
    @FXML
    public void handleMouseClick(MouseEvent event)
    {
        int index=list.getSelectionModel().getSelectedIndex();
        fill(index);
    }
    
    //Pour pouvoir naviguer avec les flèches haut et bas dynamiquement
    @FXML
    public void handleKeyPress(KeyEvent event)
    {
        int index=list.getSelectionModel().getSelectedIndex();
        if(event.getCode()==KeyCode.DOWN && index<data.size()-1)
        {
            index++;
        }else if(event.getCode()==KeyCode.UP && index>0)
        {
            index--;
        }
        fill(index);
    }
    
   //Remplissage des champs
    public void fill(int index)
    {
        ObservableList<String> row = FXCollections.observableArrayList(data.get(index));
        code.setText(row.get(0));
        fam.setText(row.get(2));
        compo.setText(row.get(3));
        effet.setText(row.get(4));
        contre.setText(row.get(5));
    }
    //Initialisation du contrôleur
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
        medicineModel = model.getMedicineModel();
        data = medicineModel.result();
        for(int i=0; i<data.size(); i++)
        { //Récupération des noms des médicaments pour la liste
            ObservableList<String> row = FXCollections.observableArrayList(data.get(i));
            list.getItems().add(row.get(1));
        }
    } 
}
