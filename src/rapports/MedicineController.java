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
{
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
    private ObservableList<ObservableList<String>> data;
    private Model model;
    private MedicineModel medicineModel;
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException
    {
        if(event.getSource()==back)
        {
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
        {
            if(model.getNewReportModel().getMed1()==null)
            {
                model.getNewReportModel().setMed1(
                list.getSelectionModel().getSelectedItem());
                remp1.setText(remp1.getText()+" "+model.getNewReportModel().getMed1());
                remp1.setVisible(true);
                
            }
            else if(model.getNewReportModel().getMed2()==null)
            {
                model.getNewReportModel().setMed2(
                list.getSelectionModel().getSelectedItem());
                remp2.setText(remp2.getText()+" "+model.getNewReportModel().getMed2());
                remp2.setVisible(true);
                med.setVisible(false);
            }
        }
        else if(event.getSource()==remp1)
        {
            model.getNewReportModel().setMed1(
            list.getSelectionModel().getSelectedItem());
            remp1.setText("Remplacer "+model.getNewReportModel().getMed1());
        }
        else if(event.getSource()==remp2)
        {
            model.getNewReportModel().setMed2(
            list.getSelectionModel().getSelectedItem());
            remp2.setText("Remplacer "+model.getNewReportModel().getMed2());
        }
    }
    
    @FXML
    public void handleMouseClick(MouseEvent event)
    {
        int index=list.getSelectionModel().getSelectedIndex();
        ObservableList<String> row = FXCollections.observableArrayList(data.get(index));
        code.setText(row.get(0));
        fam.setText(row.get(2));
        compo.setText(row.get(3));
        effet.setText(row.get(4));
        contre.setText(row.get(5));
    }
    
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
        ObservableList<String> row = FXCollections.observableArrayList(data.get(index));
        code.setText(row.get(0));
        fam.setText(row.get(2));
        compo.setText(row.get(3));
        effet.setText(row.get(4));
        contre.setText(row.get(5));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
        medicineModel = model.getMedicineModel();
        data = medicineModel.result();
        for(int i=0; i<data.size(); i++)
        {
            ObservableList<String> row = FXCollections.observableArrayList(data.get(i));
            list.getItems().add(row.get(1));
        }
    } 
}
