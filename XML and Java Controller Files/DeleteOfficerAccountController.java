package semester.project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javaClasses.admin;
import javaClasses.connectionEstablishment;
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

public class DeleteOfficerAccountController implements Initializable 
{
    String query;
    connectionEstablishment ce = new connectionEstablishment();
    admin a = new admin();
    @FXML
    private Button delete;

    @FXML
    private Button load;

    @FXML
    private TextField cnic;
    @FXML
    private TableView<admin> table;

    @FXML
    private TableColumn<admin, String> colfName;

    @FXML
    private TableColumn<admin, String> colLastName;

    @FXML
    private TableColumn<admin, String> colUsername;

    @FXML
    private TableColumn<admin, String> colPhone;

    @FXML
    private TableColumn<admin, String> colCNIC;

    @FXML
    private TableColumn<admin, Integer> colAge;

    @FXML
    private TableColumn<admin, String> colGender;

    @FXML
    private TableColumn<admin, String> colBeatName;

    @FXML
    private TableColumn<admin, String> colBeltNumber;

    @FXML
    private TableColumn<admin, String> colRank;
    
    ObservableList <admin> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        delete.setVisible(false);
        cnic.setVisible(false);
        
        colfName.setCellValueFactory(new PropertyValueFactory<>("first"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colCNIC.setCellValueFactory(new PropertyValueFactory<>("CNIC"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colBeatName.setCellValueFactory(new PropertyValueFactory<>("beatName"));
        colBeltNumber.setCellValueFactory(new PropertyValueFactory<>("beltNumber"));
        colRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        deleteValue();
    }
    
    @FXML
    private void deleteValue()
    {
        table.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) 
            {
                a = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                cnic.setText(a.getCNIC());
            }
        });
    }

    @FXML
    private void loadTable(ActionEvent event) throws SQLException
    {
        delete.setVisible(true);
        table.getItems().clear();
        query = "select fName, lName, username, phoneNumber, CNIC, age, gender, beatName, empID, rank from officer where request = 'true'";
        a.setConnection(ce.createConnection());
        a.setResultSet(a.getConnection().createStatement().executeQuery(query));
        
        while(a.getResultSet().next())
        {
            String firstName = a.getResultSet().getString("fName").trim();
            String lastName = a.getResultSet().getString("lName").trim();
            String username = a.getResultSet().getString("username").trim();
            String phonenumber = a.getResultSet().getString("phoneNumber").trim();
            String CNIC = a.getResultSet().getString("CNIC").trim();
            int age = a.getResultSet().getInt("age");
            String gender = a.getResultSet().getString("gender").trim();
            String beatname = a.getResultSet().getString("beatName").trim();
            String beltnumber = a.getResultSet().getString("empID").trim();
            String rank = a.getResultSet().getString("rank").trim();
            admin ad = new admin(firstName, lastName, username, phonenumber, CNIC, age, gender, beatname, beltnumber, rank);
            list.add(ad);
            table.setItems(list);
        }
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
