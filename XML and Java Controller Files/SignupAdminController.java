

package semester.project;

import javaClasses.admin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javaClasses.connectionEstablishment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupAdminController implements Initializable 
{
    admin a = new admin();
    
    @FXML
    private Label warning;
    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    
    @FXML
    private Label message;
    
    @FXML
    private TextField username;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        message.setVisible(false);
        warning.setVisible(false);
    }    
    
    @FXML
    private void nextButtonAction(ActionEvent event) throws IOException, SQLException 
    {
        a.setFirst(firstname.getText().trim());
        a.setLast(lastname.getText().trim());
        a.setUsername(username.getText().trim());
        a.setPassword(password.getText().trim());
        if(password.getText().equals(confirmPassword.getText()))
            {
                connectionEstablishment ce = new connectionEstablishment();
                a.setConnection(ce.createConnection());
                System.out.println("Connected:" + a.getConnection());
                a.setPrepareStatement(a.getConnection().prepareStatement("insert into person values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"));
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
            
                //closing connection with db
                a.getConnection().close();
                a.getPrepareStatement().close();
           
                Parent root = FXMLLoader.load(getClass().getResource("signupAdmin1.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
           
        }
        else
        {
            warning.setVisible(true);
        }
    }
    
    @FXML
    private void signInInsteadButtonAction(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
