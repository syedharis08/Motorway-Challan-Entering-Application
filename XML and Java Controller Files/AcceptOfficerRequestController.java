package semester.project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClasses.connectionEstablishment;
import javaClasses.person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AcceptOfficerRequestController implements Initializable 
{
    connectionEstablishment ce = new connectionEstablishment();
    person p = new person();
    
    String query;
    @FXML
    private Button button;
    
    @FXML
    private Button delete;
    
    @FXML
    private TableView<person> table;

    @FXML
    private TableColumn<person, String> colUsername;

    @FXML
    private TableColumn<person, String> colPassword;

    @FXML
    private TableColumn<person, String> colFirstName;

    @FXML
    private TableColumn<person, String> colLastName;

    @FXML
    private TableColumn<person, Integer> colAge;

    @FXML
    private TableColumn<person, String> colCNIC;

    @FXML
    private TextField setUsername;

    @FXML
    private TextField setPassword;
    
    @FXML
    private TextField setCNIC;
    
    ObservableList <person> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        delete.setVisible(false);
        setCNIC.setVisible(false);
        
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colCNIC.setCellValueFactory(new PropertyValueFactory<>("CNIC"));
        setValue();
    }    
    
    @FXML
    private void setValue()
    {
        table.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) 
            {
                p = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                setUsername.setText(p.getUsername());
                setPassword.setText(p.getPassword());
                setCNIC.setText(p.getCNIC());
            }
        });
    }
    
    @FXML
    private void clickButton(ActionEvent event) throws IOException, SQLException
    {
        query = "update officer\nset username = '"+setUsername.getText()+"',\npassword = '"+setPassword.getText()+"',\nrequest = 'true' where CNIC = '"+p.getCNIC()+"'";
        System.out.print(query);
        p.setConnection(ce.createConnection());
        p.setStatement(p.getConnection().createStatement());
        p.getStatement().executeUpdate(query);
    }
    
    @FXML 
    private void checkRequest(ActionEvent event) throws IOException
    {
        try 
        {
            delete.setVisible(true);
            table.getItems().clear();
            query = "select username, password, fName, lName, age, CNIC from officer where username = 'null'";
            p.setConnection(ce.createConnection());
            p.setResultSet(p.getConnection().createStatement().executeQuery(query));
            
            while(p.getResultSet().next())
            {
                String username = p.getResultSet().getString("username");
                String password = p.getResultSet().getString("password");
                String fName = p.getResultSet().getString("fName");
                String lName = p.getResultSet().getString("lName");
                int age = p.getResultSet().getInt("age");
                String cnic = p.getResultSet().getString("CNIC");
                
                person per = new person(username, password, fName, lName, age, cnic);
                System.out.println(per);
                list.add(per);
                System.out.println(p.getResultSet().getString("fname"));
                table.setItems(list);
            }
            button.setText("Refresh Table");
        } catch (SQLException ex) {
            Logger.getLogger(AcceptOfficerRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void delete(ActionEvent event) throws SQLException
    {
        query = "delete from officer where CNIC = '"+p.getCNIC()+"'";
        p.setConnection(ce.createConnection());
        p.setStatement(p.getConnection().createStatement());
        p.getStatement().executeUpdate(query);
    }
    
    @FXML 
    private void back(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("adminMain.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}