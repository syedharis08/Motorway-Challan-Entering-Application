/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester.project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javaClasses.admin;
import javaClasses.connectionEstablishment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tariq Ali
 */
public class SignupOfficerController implements Initializable {
    
    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void nextButtonAction(ActionEvent event) throws IOException, SQLException 
    {
        admin a = new admin();
        
        a.setFirst(firstname.getText());
        a.setLast(lastname.getText());
        
        connectionEstablishment ce = new connectionEstablishment();
        a.setConnection(ce.createConnection());
        System.out.println("Connected:" + a.getConnection());
        a.setPrepareStatement(a.getConnection().prepareStatement("insert into officer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"));
        a.getPrepareStatement().setString(1, a.getFirst());
        a.getPrepareStatement().setString(2, a.getLast());
        a.getPrepareStatement().setString(3, "null");
        a.getPrepareStatement().setString(4, "null");
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
        
        a.getPrepareStatement().setString(15, "false");
        a.getPrepareStatement().executeUpdate();
        
        Parent root = FXMLLoader.load(getClass().getResource("signupOfficer1.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }    
    
    @FXML
    private void signInInsteadButtonAction(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("usernameOfficer.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
}