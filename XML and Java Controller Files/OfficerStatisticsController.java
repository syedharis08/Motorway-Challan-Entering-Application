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

public class OfficerStatisticsController implements Initializable 
{
    connectionEstablishment ce = new connectionEstablishment();
    person p = new person();
    
    @FXML
    private BarChart<String, Integer> chart;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        {
            try {
                String query ="select POfficer.officerName as name, count(chalan.code) as total\n" +
                              "from chalan\n" +
                              "join POfficer on POfficer.poEmpID = chalan.poEmpID\n" +
                              "group by POfficer.officerName";
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                p.setConnection(ce.createConnection());
                p.setResultSet(p.getConnection().createStatement().executeQuery(query));
                while(p.getResultSet().next())
                {
                    series.getData().add(new XYChart.Data<>(p.getResultSet().getString("name"),p.getResultSet().getInt("total")));
                }
                chart.getData().add(series);
            } catch (SQLException ex) {
                Logger.getLogger(OfficerStatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    
    @FXML
    private void gender(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("genderOfficerStats.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}