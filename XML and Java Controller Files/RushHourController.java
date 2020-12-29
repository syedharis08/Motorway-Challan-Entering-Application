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
import javafx.scene.chart.XYChart;
public class RushHourController implements Initializable 
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
           String query ="select hour, count(code) as code\n" +
                         "from chalan\n" +
                         "group by hour";
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            p.setConnection(ce.createConnection());
            p.setResultSet(p.getConnection().createStatement().executeQuery(query));
            while(p.getResultSet().next())
            {
                series.getData().add(new XYChart.Data<>(p.getResultSet().getString("hour")+"th",p.getResultSet().getInt("code")));
            }
            chart.getData().add(series);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(OfficerStatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
