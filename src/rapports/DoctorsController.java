/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapports;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rapports.model.DoctorsModel;
import rapports.model.Model;

/**
 *
 * @author Lucille
 */
public class DoctorsController implements Initializable
{
    @FXML private Button back;
    @FXML private Button quit;
    @FXML private TableView table;
    @FXML private TableColumn<ObservableList,String> name;
    @FXML private TableColumn<ObservableList,String> fname;
    @FXML private TableColumn<ObservableList,String> address;
    @FXML private TableColumn<ObservableList,Number> pop;
    @FXML private TableColumn<ObservableList,String> work;
    @FXML private MenuItem addDoctor;
    private ObservableList<ObservableList> data;
    private Model model;
    private DoctorsModel doctorsModel;
    
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
        else if(event.getSource()==addDoctor)
        {
            model.getNewReportModel().setPraticien(
                    name.getCellData(table.getSelectionModel().getFocusedIndex())+" "+
                    fname.getCellData(table.getSelectionModel().getFocusedIndex()));
            Stage stage = (Stage)back.getScene().getWindow();
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
        doctorsModel = model.getDoctorsModel();
        name.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> cdf)->
            new SimpleStringProperty(cdf.getValue().get(0).toString()));
        
        fname.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> cdf)->
            new SimpleStringProperty(cdf.getValue().get(1).toString()));
        
        address.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> cdf)->
            new SimpleStringProperty(cdf.getValue().get(2).toString()));
        
        pop.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, Number> cdf)->
            new SimpleFloatProperty((float)cdf.getValue().get(3)));
        
        work.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> cdf)->
            new SimpleStringProperty(cdf.getValue().get(4).toString()));
        
        data = doctorsModel.result();

        for (int i=0; i<data.size();i++)
        {
            ObservableList row = FXCollections.observableArrayList(data.get(i));
            table.getItems().add(row);
        }   
    } 
}
