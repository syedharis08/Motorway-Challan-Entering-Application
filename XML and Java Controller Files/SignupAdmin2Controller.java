package semester.project;

import javaClasses.admin;
import javaClasses.connectionEstablishment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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

public class SignupAdmin2Controller implements Initializable 
{
    connectionEstablishment ce = new connectionEstablishment();
    admin a = new admin();
    String query = new String();
    
    ObservableList <String> choiceBoxList = FXCollections.observableArrayList("JPO","APO");
    @FXML
    private TextField beatName;

    @FXML
    private TextField empID;

    @FXML
    private ChoiceBox Rank;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField email;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Rank.setValue("JPO");
        Rank.setItems(choiceBoxList);
        beatName.setText("Beat # 13");
    }    
    
    @FXML
    private void setEmpID() throws SQLException
    {
        String s = null;
        int id = 0;
        query = "select top 1 empid from person order by empid desc";
        a.setConnection(ce.createConnection());
        a.setResultSet(a.getConnection().createStatement().executeQuery(query));
        while(a.getResultSet().next())
        {
            s = a.getResultSet().getString("empid").trim();
            System.out.print(s);
            id = Integer.parseInt(s)+1;
        }
         a.setEmpID(Integer.toString(id));
         empID.setText(a.getEmpID());
    }
    
    @FXML
    private void signUpButtonAction(ActionEvent event) throws IOException, SQLException 
    {
        if(checkBox.isSelected())
        {
            //assigning values to attributes
            a.setBeatName(beatName.getText());
            a.setEmpID(empID.getText());
            a.setEmailAddress( email.getText());
            a.setRank((String) Rank.getValue());
            
            a.setConnection(ce.createConnection());
            System.out.println("Connected:" + a.getConnection());
            query = "update person\nset beatName = '"+a.getBeatName()+"',\nempID = '"+a.getEmpID()+"',\nemailAddress = '"+a.getEmailAddress()+"',\nrank = '"+a.getRank()+"'"+",\ncompleteTable = 'true'"+"\nwhere completeTable = 'false'";
            System.out.print(query);
            a.setStatement(a.getConnection().createStatement());
            int change = a.getStatement().executeUpdate(query);
            System.out.print(change+" rows affected");
        
//            closing connection with db
            a.getConnection().close();
            a.getStatement().close();
           
            //switching to next scene
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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
            System.out.print("check plz");
        }
    }
}