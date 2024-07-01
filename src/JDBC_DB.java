import java.sql.*;

public class JDBC_DB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "swapnil";
       // String updateQuery = "UPDATE employee_payroll SET Salary=? WHERE name=?";
        String selectQuery = "SELECT * FROM employee_payroll WHERE start_date BETWEEN CAST('2024-01-03' AS DATE) AND DATE (NOW()) ";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                 System.out.println("Database connected successfully!");
                System.out.println("Connection Info: " + con);
                java.sql.Driver driver = DriverManager.getDriver(url);
                System.out.println("JDBC Driver: " + driver.getClass().getName());
                java.util.Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
                while (driverList.hasMoreElements()) {
                    java.sql.Driver d = driverList.nextElement();
                    System.out.println("Registered JDBC Driver: " + d.getClass().getName());
                }
                Statement stmt = con.createStatement();
                //PreparedStatement stmt =con.prepareStatement(updateQuery);
//                stmt.setInt(1,300000);
//                stmt.setString(2,"Swapnil");
//                stmt.executeUpdate();
//                System.out.println("Updated");

                ResultSet rs = stmt.executeQuery(selectQuery);
                while (rs.next()) {
                    int emp_id = rs.getInt("employee_id");
                    String name = rs.getString("name");
                    String gender = rs.getString("gender");
                    Double salary = rs.getDouble("salary");
                    String start_date = rs.getString("start_date");
                    String ph_number = rs.getString("phone_no");
                    String address = rs.getString("Address");
                    String department = rs.getString("Department");
                    int Basic_pay = rs.getInt("Basic_pay");
                    int Deductions = rs.getInt("Deductions");
                    int Taxable_pay = rs.getInt("Taxable_pay");
                    int Income_tax = rs.getInt("Income_tax");
                    int Net_pay = rs.getInt("Net_pay");
                    System.out.println(emp_id + " " + name + " " + gender + " " + salary + " " + start_date + " " + ph_number + " " + address + " " + department + " " + Basic_pay + " " + Deductions + " " + Taxable_pay + " " + Income_tax + " " + Net_pay);
                    System.out.println();
                }
                System.out.println("----------------Sum Of Salary----------------");
                ResultSet sumset = stmt.executeQuery("SELECT gender,SUM(salary) FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender");
                while(sumset.next()){
                    System.out.println("Gender: "+ sumset.getString(1));
                    System.out.println("Salary: "+ sumset.getString(2));
                }
                System.out.println("---------------Average of Salary----------------");
                ResultSet avgset = stmt.executeQuery("Select gender,AVG(salary) FROM employee_payroll WHERE gender='M' OR gender='F' GROUP BY gender");
                while (avgset.next()){
                    System.out.println("Gender: "+ avgset.getString(1));
                    System.out.println("AVG Salary: "+ avgset.getString(2));
                }
                System.out.println("---------------Min of Salary----------------");
                ResultSet minset = stmt.executeQuery("Select gender,MIN(salary) FROM employee_payroll WHERE gender='M' OR gender='F' GROUP BY gender");
                while (minset.next()){
                    System.out.println("Gender: "+ minset.getString(1));
                    System.out.println("Min Salary: "+ minset.getString(2));
                }
                System.out.println("---------------MAX of Salary----------------");
                ResultSet maxset = stmt.executeQuery("Select gender,MAX(salary) FROM employee_payroll WHERE gender='M' OR gender='F' GROUP BY gender");
                while (maxset.next()){
                    System.out.println("Gender: "+ maxset.getString(1));
                    System.out.println("MAX Salary: "+ maxset.getString(2));
                }
                System.out.println("---------------Count of Salary----------------");
                ResultSet countset = stmt.executeQuery("Select gender,COUNT(salary) FROM employee_payroll WHERE gender='M' OR gender='F' GROUP BY gender");
                while (countset.next()){
                    System.out.println("Gender: "+ countset.getString(1));
                    System.out.println("Count Salary: "+ countset.getString(2));
                }


            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}
