import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_DB {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user= "root";
        String password= "swapnil";
        try{
            Connection con =DriverManager.getConnection(url,user,password);
            System.out.println("Database is Connected, Connection Info is - "+con);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}