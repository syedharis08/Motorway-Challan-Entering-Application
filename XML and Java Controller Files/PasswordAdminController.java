package semester.project;

import javaClasses.connectionEstablishment;
import javaClasses.admin;
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
import javafx.scene.control.PasswordField;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Tariq Ali
 */
public class PasswordAdminController implements Initializable 
{
    admin a = new admin();
    connectionEstablishment ce = new connectionEstablishment();
    
    @FXML
    private PasswordField password;

    @FXML
    private Label labelUsername;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            //setting label name as username
            String query = "select fName from person where loginStatus = 'true'";
            a.setConnection(ce.createConnection());
            System.out.println("Connected:" + a.getConnection());
            a.setStatement(a.getConnection().createStatement());
            a.setResultSet(a.getStatement().executeQuery(query));
            while(a.getResultSet().next())
            {
                a.setFirst(a.getResultSet().getString("fName"));
            }
            labelUsername.setText(a.getFirst().trim());
        } catch (SQLException ex) {
            Logger.getLogger(PasswordAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void signInButtonAction(ActionEvent event) throws IOException, SQLException 
    {
        String query = "select password from person where loginStatus = 'true' and password = '"+password.getText()+"'";
        a.setConnection(ce.createConnection());
        System.out.println("Connected:" + a.getConnection());
        a.setStatement(a.getConnection().createStatement());
        a.setResultSet(a.getStatement().executeQuery(query));
        while(a.getResultSet().next())
        {
            a.setPassword(a.getResultSet().getString("password"));
        }
        System.out.print(a.getPassword());
        
        if(a.getPassword()!= null)
        {
            //closing connection with db
            a.getConnection().close();
            a.getStatement().close();
            a.getResultSet().close();
            
            Parent root = FXMLLoader.load(getClass().getResource("adminMain.fxml"));
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
            System.out.print("Invalid Password");
        }
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException
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
}
