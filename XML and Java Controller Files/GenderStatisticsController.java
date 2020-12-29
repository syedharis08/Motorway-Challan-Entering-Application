package semester.project;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClasses.connectionEstablishment;
import javaClasses.person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Tariq Ali
 */
public class GenderStatisticsController implements Initializable 
{
    person p = new person();
    connectionEstablishment ce = new connectionEstablishment();
    @FXML
    private BarChart<String, Integer> chart;

    @FXML
    private CategoryAxis numberofchalan;

    @FXML
    private NumberAxis numbers;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            String query ="select gender, count(code) as totalChalan from chalan \n" +
                    "group by gender\n" +
                    "order by gender";
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            p.setConnection(ce.createConnection());
            p.setResultSet(p.getConnection().createStatement().executeQuery(query));
            while(p.getResultSet().next())
            {
                series.getData().add(new XYChart.Data<>(p.getResultSet().getString("gender"),p.getResultSet().getInt("totalChalan")));
            }
            chart.getData().add(series);
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(GenderStatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
