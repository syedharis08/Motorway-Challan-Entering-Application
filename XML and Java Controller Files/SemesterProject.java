package semester.project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class SemesterProject extends Application 
{    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException
    {
  launch(args);
//    connectionEstablishment ce = new connectionEstablishment();
//    admin a = new admin();
//    a.setConnection(ce.createConnection());
//            a.setPrepareStatement(a.getConnection().prepareStatement("insert into person values(?,?,?,?,?,?,?,?,?,?,?,?)"));
//            a.getPrepareStatement().setString(1, a.getfName());
//            a.getPrepareStatement().setString(2, a.getlName());
//            a.getPrepareStatement().setString(3, a.getUsername());
//            a.getPrepareStatement().setString(4, a.getPassword());
//            a.getPrepareStatement().setString(5, a.
//            a.getPrepareStatement().setInt(7, a.getAge());
//            a.getPrepareStatement().setString(8, a.getGender());
//            a.getPrepareStatement().setString(9, a.getBeatName());
//            a.getPrepareStatement().setString(10, a.getBeltNumber());
//            a.getPrepareStatement().setString(11, a.getEmailAddress());
//            a.getPrepareStatement().setString(12, a.getRank());
//            a.getPrepareStatement().executeUpdate();
            
//    p.setConnection(ce.createConnection());
//    p.setStatement(p.getConnection().createStatement());
//    String SQL = "SELECT * from person";
//    p.setResultSet(p.getStatement().executeQuery(SQL));
//    ResultSetMetaData metaData =    p.resultSetgetPhoneNumber());
//            a.getPrepareStatement().setString(6, a.getCNIC());.getMetaData();
//
//                    int numberOfColumns = metaData.getColumnCount();
//
//                    System.out.println( "Authors Table of Books Database:\n" );
//
//                    for ( int i = 1; i <= numberOfColumns; i++ )
//                            System.out.printf( "%-8s\t", metaData.getColumnName( i ));
//                    System.out.println();
//
//                    while(p.getResultSet().next()){
//                            for ( int i = 1; i <= numberOfColumns; i++ )
//                                    System.out.printf( "%-8s\t", p.getResultSet().getObject( i ));
//                            System.out.println();
//                    }



        		}
	
    }