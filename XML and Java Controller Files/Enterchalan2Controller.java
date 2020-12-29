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
import javaClasses.vehicle;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Enterchalan2Controller implements Initializable 
{
    admin a = new admin();
    String query;
    connectionEstablishment ce = new connectionEstablishment();
    
    @FXML
    private ChoiceBox<String> vehiclecolor;

    @FXML
    private ChoiceBox<String> vehiclename;

    @FXML
    private ChoiceBox<String> vehicletype;

    @FXML
    private TextField vehicleNumber;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField officerID;
    
    ObservableList <String> list    =  FXCollections.observableArrayList();
    
    ObservableList <String> list1   =  FXCollections.observableArrayList();
    
    ObservableList <String> list2   = FXCollections.observableArrayList("LTV", "HTV");

    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        vehicleName();
        vehicleColor();
        vehicletype.setItems(list2);
    }    
    
    @FXML
    private void enterChalan(ActionEvent event) throws IOException, SQLException
    {
        chalanCredentials.setVehicleName(vehiclename.getValue());
        chalanCredentials.setVehicleType(vehicletype.getValue());
        chalanCredentials.setVehicleNumber(vehicleNumber.getText());
        chalanCredentials.setVehicleColor(vehiclecolor.getValue());
//        query();
        Parent root = FXMLLoader.load(getClass().getResource("officerMain.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
    @FXML
    public void officerID()
    {
        try
        {
            query = "select empID from officer where loginStatus = 'true'";
            a.setConnection(ce.createConnection());
            a.setStatement(a.getConnection().createStatement());
            a.setResultSet(a.getStatement().executeQuery(query));
            while(a.getResultSet().next())
            {
                chalanCredentials.setEnteringOfficerID(a.getResultSet().getString("empID"));
            }
            officerID.setText(chalanCredentials.getEnteringOfficerID());
            a.getConnection().close();
            a.getStatement().close();
            a.getResultSet().close();}
            catch (Exception e)
            {
                System.out.print("invalid");
            }
    }
    
    public void vehicleName()
    {
        try 
        {
            query = "select name from vehicle";
            a.setConnection(ce.createConnection());
            a.setResultSet(a.getConnection().createStatement().executeQuery(query));
            while(a.getResultSet().next())
            {
                chalanCredentials.setVehicleName(a.getResultSet().getString("name"));
                list.add(chalanCredentials.getVehicleName());
                vehiclename.setItems(list);
                
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Enterchalan2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void vehicleColor()
    {
        try {
            query = "select name from color";
            a.setConnection(ce.createConnection());
            a.setResultSet(a.getConnection().createStatement().executeQuery(query));
            while(a.getResultSet().next())
            {
                chalanCredentials.setVehicleColor(a.getResultSet().getString("name"));
                list1.add(chalanCredentials.getVehicleColor());
                vehiclecolor.setItems(list1);
                
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Enterchalan2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void query() throws SQLException
    {
        connectionEstablishment ce = new connectionEstablishment();
        a.setConnection(ce.createConnection());
        System.out.println("Connected:" + a.getConnection());
        a.setPrepareStatement(a.getConnection().prepareStatement("insert into chalan values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"));
        a.getPrepareStatement().setString(1, a.getFirst());
        a.getPrepareStatement().setString(2, a.getLast());
        a.getPrepareStatement().setString(3, a.getUsername());
        a.getPrepareStatement().setString(4, a.getPassword());
        a.getPrepareStatement().setString(5, " ");
        a.getPrepareStatement().setString(6, " ");
        a.getPrepareStatement().setInt(7, 0);
        a.getPrepareStatement().setString(8, " ");
        a.getPrepareStatement().setString(9, " ");
        a.getPrepareStatement().setString(10, "0");
        a.getPrepareStatement().setString(11, " ");
        a.getPrepareStatement().setString(12, " ");
        a.getPrepareStatement().setString(13, "false");
        a.getPrepareStatement().setString(14, "false");
        a.getPrepareStatement().executeUpdate();
    }
}