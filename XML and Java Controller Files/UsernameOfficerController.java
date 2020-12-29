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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class UsernameOfficerController implements Initializable 
{
    connectionEstablishment ce = new connectionEstablishment();
    admin a = new admin();
    String query = new String();
    
    @FXML
    private TextField username;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            a.setConnection(ce.createConnection());
            System.out.println("Connected:" + a.getConnection());
            query = "update officer set loginStatus = 'false'";
            a.setStatement(a.getConnection().createStatement());
            a.getStatement().executeUpdate(query);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void nextButtonAction(ActionEvent event) throws IOException, SQLException 
    {
        query = "select username from officer where username = '"+username.getText()+"'";
        System.out.print(query);
        a.setStatement(a.getConnection().createStatement());
        a.setResultSet(a.getStatement().executeQuery(query));
        while(a.getResultSet().next())
        {
            a.setUsername(a.getResultSet().getString("username").trim());
        }
        System.out.print(a.getUsername()+" outside");
        
        if(a.getUsername() != null)
        {
            System.out.print(a.getUsername()+" hello");
            query = "update officer set loginStatus = 'true' where username = '"+username.getText()+"'";
            a.setStatement(a.getConnection().createStatement());
            a.getStatement().executeUpdate(query);
            Parent root = FXMLLoader.load(getClass().getResource("passwordOfficer.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        }
        
        else
        {
            System.out.print("Incorrect Username");
        }
    }    
    
    @FXML
    private void clickHere1Action(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
    @FXML
    private void clickHere2Action(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("signupOfficer.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
    @FXML
    private void clickHere3Action(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("checkAccount.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
}
