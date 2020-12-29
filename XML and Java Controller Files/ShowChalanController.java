package semester.project;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javaClasses.admin;
import javaClasses.chalan;
import javaClasses.connectionEstablishment;
import javaClasses.vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowChalanController implements Initializable 
{
    String query;
    vehicle v = new vehicle();
    admin a = new admin();
    connectionEstablishment ce = new connectionEstablishment();
    
    @FXML
    private TableView table;

    @FXML
    private TableColumn<admin, String> name;

    @FXML
    private TableColumn<admin, String> cnic;

    @FXML
    private TableColumn<admin, String> phoneNumber;

    @FXML
    private TableColumn<admin, String> age;

    @FXML
    private TableColumn<chalan, String> reason;

    @FXML
    private TableColumn<chalan, String> date;

    @FXML
    private TableColumn<chalan, String> time;

    @FXML
    private TableColumn<admin, String> officerName;

    @FXML
    private TableColumn<vehicle, String> vehicleName;

    @FXML
    private TableColumn<vehicle, String> vehicleType;
    
    ObservableList list = FXCollections.observableArrayList();
    ObservableList list1 = FXCollections.observableArrayList();
    ObservableList list2 = FXCollections.observableArrayList();
    ObservableList list3 = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try {
            name.setCellValueFactory(new PropertyValueFactory<>("first"));
            cnic.setCellValueFactory(new PropertyValueFactory<>("CNIC"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            age.setCellValueFactory(new PropertyValueFactory<>("age"));
            reason.setCellValueFactory(new PropertyValueFactory<>("description"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            officerName.setCellValueFactory(new PropertyValueFactory<>("last"));
            vehicleName.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
            vehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
            query = "select fname,cnic,phonenumber,age,description,dateofchalan,time,officername,vehiclename,vehicletype from chalan";
            
            a.setConnection(ce.createConnection());
            a.setResultSet(a.getConnection().createStatement().executeQuery(query));
            while(a.getResultSet().next())
            {
                String sfirst = a.getResultSet().getString("fname").trim();
                String sCNIC = a.getResultSet().getString("cnic").trim();
                String sphoneNumber = a.getResultSet().getString("phonenumber").trim();
                int sage = a.getResultSet().getInt("age");
                String sdescription = a.getResultSet().getString("description").trim();
                String sdate = a.getResultSet().getString("dateofchalan").trim();
                String stime = a.getResultSet().getString("time").trim();
                String slast = a.getResultSet().getString("officername").trim();
                String svehicleName = a.getResultSet().getString("vehiclename").trim();
                String svehicleType = a.getResultSet().getString("vehicletype").trim();
                admin adm = new admin(sfirst, sCNIC, sphoneNumber, sage, slast);
                vehicle veh = new vehicle(svehicleName, svehicleType);
                chalan ch = new chalan(sdescription, sdate, stime);
                
                list.add(adm);
                list.add(veh);
                list.add(ch);
                table.setItems(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowChalanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    @FXML
    private void loadTable(ActionEvent event)
    {
        
    }
}
