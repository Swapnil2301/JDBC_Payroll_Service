import java.sql.*;

public class JDBC_DB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "swapnil";
        String updateQuery = "UPDATE employee_payroll SET Salary=? WHERE name=?";
        String selectQuery = "SELECT * FROM employee_payroll";

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
                PreparedStatement stmt =con.prepareStatement(updateQuery);
                stmt.setInt(1,300000);
                stmt.setString(2,"Swapnil");
                stmt.executeUpdate();
                System.out.println("Updated");

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

                con.close();
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}
