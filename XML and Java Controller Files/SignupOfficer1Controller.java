package semester.project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javaClasses.admin;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tariq Ali
 */
public class SignupOfficer1Controller implements Initializable 
{
    
    admin a = new admin();
    
    int ageSimple;
    ObservableList <String> choiceBoxList = FXCollections.observableArrayList("Male", "Female");
    
    @FXML
    private TextField phonenumber;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private ChoiceBox gender;

    @FXML
    private TextField CNIC;
    
    @FXML
    private TextField age;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        gender.setValue("Male");
        gender.setItems(choiceBoxList);
    }
    
    @FXML private void showAge()
    {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int birthYear = dateOfBirth.getValue().getYear();
        ageSimple = year - birthYear;
        age.setText(Integer.toString(ageSimple)+" Years");
    }

    @FXML
    private void nextButtonAction(ActionEvent event) throws IOException, SQLException 
    {
        a.setPhoneNumber(phonenumber.getText());
        a.setCNIC(CNIC.getText());
        a.setAge(ageSimple);
        a.setGender((String) gender.getValue());
        
        connectionEstablishment ce = new connectionEstablishment();
        a.setConnection(ce.createConnection());
        System.out.println("Connected:" + a.getConnection());
        String query = "update officer\nset phoneNumber = '"+a.getPhoneNumber()+"',\nCNIC = '"+a.getCNIC()+"',\nage = '"+a.getAge()+"',\ngender = '"+a.getGender()+"'"+"\nwhere loginStatus = 'false'";
        System.out.print(query);
        a.setStatement(a.getConnection().createStatement());
        int change = a.getStatement().executeUpdate(query);
        System.out.print(change+" rows affected");
        Parent root = FXMLLoader.load(getClass().getResource("signupOfficer2.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }    
    
}
