package semester.project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClasses.admin;
import javaClasses.chalanCredentials;
import javaClasses.connectionEstablishment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Enterchalan1Controller implements Initializable 
{
    String query;
    connectionEstablishment ce = new connectionEstablishment();
    admin a = new admin();
    @FXML
    private ChoiceBox<String> pOfficerId;

    @FXML
    private TextField pofficerName;

    @FXML
    private TextField booknumber;

    @FXML
    private ChoiceBox<String> location;

    @FXML
    private ChoiceBox<String> cOfficerId;

    @FXML
    private TextField cOfficerName;
    
    ObservableList <String> list = FXCollections.observableArrayList();
    
    ObservableList <String> list1 = FXCollections.observableArrayList();
    
    ObservableList <String> list2 = FXCollections.observableArrayList("North", "South");

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        location.setItems(list2);
        pempid();
        cempid();
    }
    
    @FXML
    private void next(ActionEvent event) throws IOException, SQLException
    {
        chalanCredentials.setPoEmpID(pOfficerId.getValue());
        chalanCredentials.setBookNumber(booknumber.getText().trim());
        chalanCredentials.setCuEmpID(cOfficerId.getValue());
        chalanCredentials.setLocation(location.getValue().trim());
        System.out.print(chalanCredentials.getPoEmpID()+chalanCredentials.getCuEmpID());
        Parent root = FXMLLoader.load(getClass().getResource("enterchalan2.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
    @FXML
    private void setpOfficerName()
    {
        try
        {
            query = "select officerName from Pofficer where poEmpID = "+pOfficerId.getValue().trim();
            a.setConnection(ce.createConnection());
            a.setStatement(a.getConnection().createStatement());
            a.setResultSet(a.getStatement().executeQuery(query));
            while(a.getResultSet().next())
            {
                chalanCredentials.setpName(a.getResultSet().getString("officerName"));
            }
            pofficerName.setText(chalanCredentials.getpName());
            a.getConnection().close();
            a.getStatement().close();
            a.getResultSet().close();}
            catch (Exception e)
            {
                System.out.print("invalid");
            }
    }
    
    @FXML
    private void setcOfficerName()
    {
        try
        {
            query = "select officerName from cuofficer where cuEmpID = "+cOfficerId.getValue().trim();
            a.setConnection(ce.createConnection());
            a.setStatement(a.getConnection().createStatement());
            a.setResultSet(a.getStatement().executeQuery(query));
            while(a.getResultSet().next())
            {
                chalanCredentials.setCuName(a.getResultSet().getString("officerName"));
            }
            cOfficerName.setText(chalanCredentials.getCuName());
            a.getConnection().close();
            a.getStatement().close();
            a.getResultSet().close();}
            catch (SQLException e)
            {
                System.out.print("invalid");
            }
    }
    
    public void pempid()
    {
        try {
            query = "select poempid from POfficer";
            a.setConnection(ce.createConnection());
            a.setResultSet(a.getConnection().createStatement().executeQuery(query));
            while(a.getResultSet().next())
            {
                chalanCredentials.setPoEmpID(a.getResultSet().getString("poempid").trim());
                list.add(chalanCredentials.getPoEmpID());
                pOfficerId.setItems(list);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enterchalan1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cempid()
    {
        try {
            query = "select cuempid from cuOfficer";
            a.setConnection(ce.createConnection());
            a.setResultSet(a.getConnection().createStatement().executeQuery(query));
            while(a.getResultSet().next())
            {
                a.setEmpID(a.getResultSet().getString("cuempid").trim());
                list1.add(a.getEmpID());
                cOfficerId.setItems(list1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enterchalan1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
