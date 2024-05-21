import java.sql.*;
import java.util.*;
public class SystemAdministrator extends User {
    public SystemAdministrator(String username, String password) {
        super(username, password);
    }
    public void AddingNewProfile(){
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the username of the new user: ");
            String username = scanner.nextLine().trim();



            System.out.print("Enter the password of the new user: ");
            String password = scanner.nextLine().trim();


            System.out.print("Enter the role of the new user: ");
            String role = scanner.nextLine().trim();


            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();

            String addUserQuery = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addUserQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
            System.out.println("User successfully added to the users table.");

            ResultSet resultSet = statement.executeQuery("SELECT ID, username, password, role FROM users");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("╭───────────────────────────────────────────────────────────╮");
            System.out.printf("|%-10s %-20s %-20s %-20s%n", "ID", "Username", "Password", "Role|");
            System.out.println("|------------------------------------------------------------|");

            while (resultSet.next()) {
                int idValue = resultSet.getInt("ID");
                String username1 = resultSet.getString("username");
                String passwordValue = resultSet.getString("password");
                String roleValue = resultSet.getString("role");
                System.out.printf("|%-10d %-20s %-20s %-20s%n", idValue, username1, passwordValue, roleValue,"|");
            }

            System.out.println("╰───────────────────────────────────────────────────────────╯");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void EditingProfile(){
        try {
            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID, username, password, role FROM users");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("╭───────────────────────────────────────────────────────────╮");
            System.out.printf("|%-10s %-20s %-20s %-20s%n", "ID", "Username", "Password", "Role|");
            System.out.println("|------------------------------------------------------------|");

            while (resultSet.next()) {
                int idValue = resultSet.getInt("ID");
                String username1 = resultSet.getString("username");
                String passwordValue = resultSet.getString("password");
                String roleValue = resultSet.getString("role");
                System.out.printf("|%-10d %-20s %-20s %-20s%n", idValue, username1, passwordValue, roleValue,"|");
            }

            System.out.println("╰───────────────────────────────────────────────────────────╯");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID of the user to update: ");
            int userId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the number of the column to update (1 for Username, 2 for Password, 3 for Role): ");
            int columnNumber = scanner.nextInt();
            scanner.nextLine();

            String columnName = "";
            switch (columnNumber) {
                case 1:
                    columnName = "username";
                    break;
                case 2:
                    columnName = "password";
                    break;
                case 3:
                    columnName = "role";
                    break;
                default:
                    System.out.println("Incorrect column number.");
                    return;
            }

            System.out.print("Enter the new value: ");
            String newValue = scanner.nextLine();

            String updateUserQuery = "UPDATE users SET " + columnName + " = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateUserQuery);
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, userId);
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows > 0) {
                System.out.println("User with ID " + userId + " successfully updated in the users table.");
            } else {
                System.out.println("No user found with ID " + userId + ".");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void  deletingProfile(){
        try {
            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID, username, password, role FROM users");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("╭───────────────────────────────────────────────────────────╮");
            System.out.printf("|%-10s %-20s %-20s %-20s%n", "ID", "Username", "Password", "Role|");
            System.out.println("|------------------------------------------------------------|");

            while (resultSet.next()) {
                int idValue = resultSet.getInt("ID");
                String username1 = resultSet.getString("username");
                String passwordValue = resultSet.getString("password");
                String roleValue = resultSet.getString("role");
                System.out.printf("|%-10d %-20s %-20s %-20s%n", idValue, username1, passwordValue, roleValue,"|");
            }

            System.out.println("╰───────────────────────────────────────────────────────────╯");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID of the user to delete: ");
            int userId = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            String deleteUserQuery = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteUserQuery);
            preparedStatement.setInt(1, userId);
            int deletedRows = preparedStatement.executeUpdate();

            if(deletedRows > 0) {
                System.out.println("User with ID " + userId + " successfully deleted from the users table.");
            } else {
                System.out.println("No user found with ID " + userId + ".");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void CreatingCourse (){
        try (Connection connection = MyJDBC.getConnection();
             Statement statement = connection.createStatement();
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter the name of the new course: ");
            String courseName = scanner.nextLine().trim();


            System.out.print("Enter the description of the new course: ");
            String courseDescription = scanner.nextLine().trim();

            String insertQuery = "INSERT INTO cource (COURSENAME, DESCRIPTIONOFCOURSE) VALUES (?, ?)";
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery)) {
                preparedStatement1.setString(1, courseName);
                preparedStatement1.setString(2, courseDescription);
                preparedStatement1.executeUpdate();
                System.out.println("Course successfully added to the course table.");
            }

            try (ResultSet resultSet = statement.executeQuery("SELECT ID, COURSENAME, DESCRIPTIONOFCOURSE FROM cource")) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                System.out.println("╭────────────────────────────────────────────────────────────────────────────────────╮");
                System.out.printf("|%-10s %-30s %-50s%n", "CourseID", "Course Name", "Description of Course|");
                System.out.println("|------------------------------------------------------------------------------------|");

                while (resultSet.next()) {
                    int courseID = resultSet.getInt("ID");
                    String courseNameResult = resultSet.getString("COURSENAME");
                    String courseDescriptionResult = resultSet.getString("DESCRIPTIONOFCOURSE");
                    System.out.printf("|%-10d %-30s %-50s%n", courseID, courseNameResult, courseDescriptionResult);
                }

                System.out.println("╰────────────────────────────────────────────────────────────────────────────────────╯");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deletingCourse(){
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cource WHERE COURSEID = ?");
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter the ID of the course to delete: ");
            int courseId = scanner.nextInt();
            scanner.nextLine();

            preparedStatement.setInt(1, courseId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course with ID " + courseId + " successfully deleted from the course table.");
            } else {
                System.out.println("No course found with ID " + courseId + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }public  void  editingCourse(){
        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cource SET COURSENAME = ?, DESCRIPTIONOFCOURSE = ? WHERE ID = ?");
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter the ID of the course to update: ");
            int courseId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Choose the column to update:");
            System.out.println("1. Course Name");
            System.out.println("2. Description of Course");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String columnName;
            String newValue;

            switch (choice) {
                case 1:
                    columnName = "COURSENAME";
                    System.out.print("Enter the new name of the course: ");
                    newValue = scanner.nextLine().trim();
                    break;
                case 2:
                    columnName = "DESCRIPTIONOFCOURSE";
                    System.out.print("Enter the new description of the course: ");
                    newValue = scanner.nextLine().trim();
                    break;
                default:
                    System.out.println("Invalid choice. No changes will be made.");
                    return;
            }

            preparedStatement.setString(1, columnName);
            preparedStatement.setString(2, newValue);
            preparedStatement.setInt(3, courseId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course with ID " + courseId + " successfully updated in the course table.");
            } else {
                System.out.println("No course found with ID " + courseId + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void menu(){
        System.out.println("╭-----------------------------------------------------------╮");
        System.out.println("|                CHOOSE AN OPTION ,YOU WANT TO DO           |");
        System.out.println("|                1-CREATING PROFILE                         |");
        System.out.println("|                2-DELETING PROFILE                         |");
        System.out.println("|                3-EDITING PROFILE                           |");
        System.out.println("|                4-CREATING COURSE                          |");
        System.out.println("|                5-DELETING COURSE                          |");
        System.out.println("|                6-EDITING COURSE                           |");
        System.out.println("|                7-EXIT                                     |");
        System.out.println("╰-----------------------------------------------------------╯");


    }
}
