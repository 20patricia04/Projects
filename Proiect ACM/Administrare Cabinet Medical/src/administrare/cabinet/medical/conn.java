package administrare.cabinet.medical;
import java.sql.*;

public class conn {

    Connection connection;
    Statement statement;

    public conn(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/administrare_cabinet_medical","root","patrimac2023");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
