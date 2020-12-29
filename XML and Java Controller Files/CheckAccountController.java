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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tariq Ali
 */
public class CheckAccountController implements Initializable 
{
    
    connectionEstablishment ce = new connectionEstablishment();
    admin a = new admin();
    @FXML
    private Button signin;
  
    @FXML
    private Button buttton;

    @FXML
    private TextField cnic;

    @FXML
    private TextField username;

    @FXML
    private TextField password;
    
    @FXML
    private Label label;
    
    @FXML
    private Label userlabel;

    @FXML
    private Label passwordlabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        passwordlabel.setVisible(false);
        userlabel.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        signin.setVisible(false);
        label.setVisible(false);
    }
    @FXML
    private void enterCnic(ActionEvent event) throws IOException, SQLException
    {
        System.out.print(cnic.getText());
        String query = "select CNIC, username, password from officer where CNIC = '"+cnic.getText()+"'";
        System.out.print(query);
        
        a.setConnection(ce.createConnection());
        a.setStatement(a.getConnection().createStatement());
        a.setResultSet(a.getStatement().executeQuery(query));
        while(a.getResultSet().next())
        {
            a.setCNIC(a.getResultSet().getString("CNIC"));
            a.setUsername(a.getResultSet().getString("username"));
            a.setPassword(a.getResultSet().getString("password"));
        }
        
        try{
            buttton.setVisible(false);
            cnic.setVisible(false);
            username.setVisible(true);
            password.setVisible(true);
            signin.setVisible(true);
            passwordlabel.setVisible(true);
            userlabel.setVisible(true);
            username.setText(a.getUsername());
            password.setText(a.getPassword());
        }
        catch(Exception e)
        {
            label.setVisible(true);
        }
    }    
    
    
    @FXML
    private void signInButtonAction(ActionEvent event) throws IOException
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
