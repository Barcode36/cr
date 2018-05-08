/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapports;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rapports.model.Model;
import rapports.model.ReportModel;

/**
 *
 * @author Lucille
 */
public class ReportController implements Initializable
{
    @FXML private Button quit;
    @FXML private Button previous;
    @FXML private Button next;
    @FXML private Button back;
    @FXML private Button newReport;
    @FXML private ComboBox<String> motive;
    @FXML private ComboBox<String> med1;
    @FXML private ComboBox<String> med2;
    @FXML private TextField num;
    @FXML private TextField praticien;
    @FXML private DatePicker dateRap;
    @FXML private TextArea bilan;
    private ObservableList<ObservableList<String>> data;
    private Model model;
    private ReportModel reportModel;
    private int index;
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException
    {
        if(event.getSource()==quit)
        {
            System.exit(0);
        }
        else if(event.getSource()==previous)
        {
            if(index>0){
                index--;
                ObservableList<String> row = FXCollections.observableArrayList(data.get(index));
                System.out.println(data.get(index));
                num.setText(row.get(0));
                praticien.setText(row.get(1));
                dateRap.setValue(LocalDate.parse(row.get(2), DateTimeFormatter.ISO_DATE));
                bilan.setText(row.get(4));
                motive.setItems(row);
                med1.setItems(row);
                med2.setItems(row);
                motive.getSelectionModel().select(3);
                med1.getSelectionModel().select(5);
                med2.getSelectionModel().select(6);
            }
        }
        else if(event.getSource()==next)
        {
            if(index<data.size()-1){
                index++;
                ObservableList<String> row = FXCollections.observableArrayList(data.get(index));
                num.setText(row.get(0));
                praticien.setText(row.get(1));
                dateRap.setValue(LocalDate.parse(row.get(2), DateTimeFormatter.ISO_DATE));
                bilan.setText(row.get(4));
                motive.setItems(row);
                med1.setItems(row);
                med2.setItems(row);
                motive.getSelectionModel().select(3);
                med1.getSelectionModel().select(5);
                med2.getSelectionModel().select(6);
            }
        }
        else if(event.getSource()==back)
        {
            Stage stage = (Stage)back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/MenuView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==newReport)
        {
            Stage stage = (Stage)newReport.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/NewReportView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @Override 
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
        reportModel = model.getReportModel();
        data = reportModel.result();
        index = 0;
        ObservableList<String> row = FXCollections.observableArrayList(data.get(index));
        num.setText(row.get(0));
        praticien.setText(row.get(1));
        dateRap.setValue(LocalDate.parse(row.get(2), DateTimeFormatter.ISO_DATE));
        bilan.setText(row.get(4));
        motive.setItems(row);
        med1.setItems(row);
        med2.setItems(row);
        motive.getSelectionModel().select(3);
        med1.getSelectionModel().select(5);
        med2.getSelectionModel().select(6);
        
    } 
}
