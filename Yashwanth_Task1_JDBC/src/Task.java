import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Task {

	private static Scanner sc;

	private static void options() {
		System.out.println("To Create a record, Enter 1");
		System.out.println("To Read a record, Enter 2");
		System.out.println("To Update a record, Enter 3");
		System.out.println("To Delete a record, Enter 4");
		System.out.println("To Exit, Enter 5");
	}

	public static void main(String[] args) throws SQLException {
		int option;
		String EmpName;
		int EmpId;
		String confirm;
		sc = new Scanner(System.in);
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "Mysql@123");
		System.out.println("Connection status : Success");

		while (true) {
			Statement st = c.createStatement();
			options();
			option = sc.nextInt();
			switch (option) {
			case 1:
				System.out.println("Enter a new 5 digit id of the employee");
				EmpId = sc.nextInt();
				System.out.println("Enter the name of the employee");
				EmpName = sc.next();
				st.execute("INSERT into employee Values (" + EmpId + "," + "'" + EmpName + "'" + ")");
				System.out.println("The record has been successfully inserted");
				break;
			case 2:
				ResultSet result = st.executeQuery("SELECT * FROM employee;");
				System.out.println("************************************************");
				while (result.next()) {
					System.out.println("Employee ID :" + result.getInt("EmployeeId") + "\n" + "Employee Name :"
							+ result.getString("EmployeeName"));
					System.out.println("------------------------------------------------");
				}
				System.out.println("************************************************");

				break;
			case 3:
				System.out.println("Enter a new 5 digit id of the employee");
				EmpId = sc.nextInt();
				System.out.println("Enter the new name of the employee");
				EmpName = sc.next();
				st.execute("UPDATE employee set EmployeeName='" + EmpName + "' WHERE EmployeeId=" + EmpId + ";");
				System.out.println("The record has been successfully updated");
				break;
			case 4:
				System.out.println("Enter the Employee name you want to delete");
				EmpName = sc.next();
				st.execute("DELETE FROM employee WHERE EmployeeName='" + EmpName + "';");
				System.out.println("The record has been successfully deleted");
				break;
			case 5:
				break;
			default:
				System.out.println("Please select a valid option");
				break;

			}
			System.out.println("Enter any key to continue or Enter " + "'no'" + " to leave");
			confirm = sc.next();
			if (confirm.equalsIgnoreCase("no")) {
				break;

			}

		}
		System.out.println("Thanks for using our application, Bye");

	}
}
