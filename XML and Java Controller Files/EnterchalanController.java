package semester.project;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClasses.chalan;
import javaClasses.chalanCredentials;
import javaClasses.connectionEstablishment;
import javaClasses.person;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class EnterchalanController implements Initializable 
{
    
    ObservableList <Integer> hourList = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12);
    ObservableList <Integer> minuteList = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60);
    ObservableList <String> am_pmList = FXCollections.observableArrayList("am", "pm");
    ObservableList <String> genderList = FXCollections.observableArrayList("Male", "Female");
    ObservableList <String> cityList = FXCollections.observableArrayList("Lahore", "Karachi", "Multan", "Peshawar", "Islamabad", "Rawalpindi");
    
    
    String query;
    chalanCredentials c = new chalanCredentials();
    connectionEstablishment ce = new connectionEstablishment();
    person p = new person();
    
    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField cnic;

    @FXML
    private ChoiceBox<String> city;

    
    @FXML
    private TextField phonenumber;

    @FXML
    private TextField age;
    
    @FXML
    private TextField chalanCode;

    @FXML
    private TextField description;
    
    @FXML
    private TextField penalty;
    
    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox<Integer> hours;

    @FXML
    private ChoiceBox<Integer> minute;

    @FXML
    private ChoiceBox<String> am_pm;
    
    
    @FXML
    private ChoiceBox<String> gender;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        hours.setValue(00);
        minute.setValue(00);
        am_pm.setValue("am");
        hours.setItems(hourList);
        minute.setItems(minuteList);
        am_pm.setItems(am_pmList);
        gender.setItems(genderList);
        city.setItems(cityList);
    }

    @FXML
    private void next(ActionEvent event) throws IOException, SQLException
    {
       int month = date.getValue().getMonthValue();
       int day = date.getValue().getDayOfMonth();
       int year = date.getValue().getYear();
       chalanCredentials.setFirst(firstname.getText().trim());
       chalanCredentials.setLast(lastname.getText().trim());
       chalanCredentials.setCnic(cnic.getText().trim());
       chalanCredentials.setPhoneNumber(phonenumber.getText().trim());
       chalanCredentials.setCity(city.getValue().trim());
       chalanCredentials.setAge(Integer.parseInt(age.getText().trim()));
       chalanCredentials.setGender(gender.getValue().trim());
       chalanCredentials.setCode(chalanCode.getText().trim());
       chalanCredentials.setDateOfChalan(Integer.toString(day).trim()+"/"+Integer.toString(month).trim()+"/"+Integer.toString(year).trim());
       chalanCredentials.setHour(hours.getValue());
       chalanCredentials.setMinuter(minute.getValue());
       chalanCredentials.setDay(am_pm.getValue());
       Parent root = FXMLLoader.load(getClass().getResource("enterchalan1.fxml"));
       Scene scene = new Scene(root);
       Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
       Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
       stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
       stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
    @FXML
    public void setDescriptionValue() throws SQLException
    {
        query = "select description from penalty where code = '"+chalanCode.getText().trim()+"'";
        p.setConnection(ce.createConnection());
        p.setStatement(p.getConnection().createStatement());
        p.setResultSet(p.getStatement().executeQuery(query));
        while(p.getResultSet().next())
        {
            chalanCredentials.setDescription(p.getResultSet().getString("description").trim());
        }
        
        description.setText(chalanCredentials.getDescription());
        p.getConnection().close();
        p.getStatement().close();
        p.getResultSet().close();
    }
    
    @FXML
    public void setPenaltyValue() throws SQLException
    {
        query = "select penalty from penalty where code = '"+chalanCode.getText().trim()+"'";
        p.setConnection(ce.createConnection());
        p.setStatement(p.getConnection().createStatement());
        p.setResultSet(p.getStatement().executeQuery(query));
        while(p.getResultSet().next())
        {
            chalanCredentials.setPenalty(p.getResultSet().getInt("penalty"));
        }
        
        penalty.setText(Integer.toString(chalanCredentials.getPenalty()));
        p.getConnection().close();
        p.getStatement().close();
        p.getResultSet().close();
    }
}
