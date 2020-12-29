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
import javaClasses.connectionEstablishment;
import javaClasses.person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tariq Ali
 */
public class GenderOfficerStatsController implements Initializable 
{
    connectionEstablishment ce = new connectionEstablishment();
    person p = new person();
    
    @FXML
    private BarChart<String, Integer> chart;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
           String query ="select POfficer.officerGender as gender, count(chalan.code) as chalan\n" +
                          "from chalan\n" +
                          "join POfficer on POfficer.poEmpID = chalan.poEmpID\n" +
                          "group by POfficer.officerGender";
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            p.setConnection(ce.createConnection());
            p.setResultSet(p.getConnection().createStatement().executeQuery(query));
            while(p.getResultSet().next())
            {
                series.getData().add(new XYChart.Data<>(p.getResultSet().getString("gender"),p.getResultSet().getInt("chalan")));
            }
            chart.getData().add(series);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(OfficerStatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    @FXML
    public void officerStats(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("officerStatistics.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
