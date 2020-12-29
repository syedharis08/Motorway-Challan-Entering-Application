package semester.project;

import javaClasses.admin;
import javaClasses.connectionEstablishment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    connectionEstablishment ce = new connectionEstablishment();
    admin a = new admin();
    String query = new String();
    
    @FXML
    private Label usernameLabel;

    
    @FXML
    private TextField username;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        usernameLabel.setVisible(false);
        try 
        {
            a.setConnection(ce.createConnection());
            System.out.println("Connected:" + a.getConnection());
            query = "update person set loginStatus = 'false'";
            a.setStatement(a.getConnection().createStatement());
            a.getStatement().executeUpdate(query);
        } 
        catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void nextButtonAction(ActionEvent event) throws IOException, SQLException 
    {
        boolean check = validateUsername();
        if(check == true)
        {
            Parent root = FXMLLoader.load(getClass().getResource("PasswordAdmin.fxml"));
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
            usernameLabel.setVisible(true);
        }
    }    
    
    @FXML
    private void clickHere1Action(ActionEvent event) throws IOException
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
    
    @FXML
    private void clickHere2Action(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("signupAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
    private boolean validateUsername() throws SQLException
    {
        query = "select username from person where username = '"+username.getText().trim()+"'";
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
            query = "update person set loginStatus = 'true' where username = '"+username.getText()+"'";
            a.setStatement(a.getConnection().createStatement());
            a.getStatement().executeUpdate(query);
            return true;
        }
        else
            return false;
    }
}
