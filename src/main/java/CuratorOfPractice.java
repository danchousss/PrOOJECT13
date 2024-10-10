import java.sql.*;
import java.util.*;
public class CuratorOfPractice extends User {
    public CuratorOfPractice(String username, String password) {

        super(username, password);

    }
    public void makingAListOfStudents(){
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the name of the new group: ");
            String newColumnName = scanner.nextLine().trim();

            if (newColumnName.isEmpty() || !newColumnName.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                System.out.println("Incorrect group name.");
                return;
            }

            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM groupsofstudents LIMIT 1");
            ResultSetMetaData metaData = resultSet.getMetaData();
            boolean columnExists = false;
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                if (metaData.getColumnName(i).equalsIgnoreCase(newColumnName)) {
                    columnExists = true;
                    break;
                }
            }

            if (!columnExists) {
                String alterTableQuery = "ALTER TABLE groupsofstudents ADD COLUMN " + newColumnName + " VARCHAR(100)";
                statement.executeUpdate(alterTableQuery);
                System.out.println("New column '" + newColumnName + "' successfully added to the groupsofstudents table.");
            } else {
                System.out.println("Column '" + newColumnName + "' already exists in the groupsofstudents table.");
            }

            resultSet = statement.executeQuery("SELECT * FROM groupsofstudents");
            metaData = resultSet.getMetaData();
            columnCount = metaData.getColumnCount();

            System.out.println("╭-----------------------------------------------------------╮");

            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("| %-30s ", metaData.getColumnName(i));
            }
            System.out.println("|");
            System.out.println("├-----------------------------------------------------------┤");

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = resultSet.getString(i);
                    System.out.printf("| %-30s ", value);
                }
                System.out.println("|");
            }
            System.out.println("╰-----------------------------------------------------------╯");


            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void FillingAList(){
        try {
            Scanner scanner = new Scanner(System.in);

            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSetMetaData metaData = statement.executeQuery("SELECT * FROM groupsofstudents").getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.println("Available Columns:");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(i + ". " + metaData.getColumnName(i));
            }

            System.out.print("Enter the number of the column to add students: ");
            int columnNumber = scanner.nextInt();
            scanner.nextLine();

            if (columnNumber < 1 || columnNumber > columnCount) {
                System.out.println("Incorrect column number.");
                return;
            }

            String selectedColumn = metaData.getColumnName(columnNumber);
            System.out.println("Selected Column: " + selectedColumn);

            while (true) {
                System.out.print("Enter the name of the student to add (or type 'exit' to finish): ");
                String studentName = scanner.nextLine();
                if (studentName.equalsIgnoreCase("exit")) {
                    break;
                }

                String insertQuery = "INSERT INTO groupsofstudents (" + selectedColumn + ") VALUES (?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, studentName);
                preparedStatement.executeUpdate();
                System.out.println("Student '" + studentName + "' successfully added to " + selectedColumn + ".");
            }

            ResultSet resultSet = statement.executeQuery("SELECT * FROM groupsofstudents");
            metaData = resultSet.getMetaData();
            columnCount = metaData.getColumnCount();

            System.out.println("╭───────────────────────────────────────────────────────────╮");
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("| %-20s ", metaData.getColumnName(i));
            }
            System.out.println("|");

            System.out.print("├");
            for (int i = 0; i < columnCount; i++) {
                System.out.print("──────────────────────────");
                if (i < columnCount - 1) System.out.print("┼");
            }
            System.out.println("┤");

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("| %-20s ", resultSet.getString(i));
                }
                System.out.println("|");
            }

            System.out.println("╰───────────────────────────────────────────────────────────╯");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void menu(){
        System.out.println("╭-----------------------------------------------------------╮");
        System.out.println("|                CHOOSE AN OPTION ,YOU WANT TO DO           |");
        System.out.println("|                1-MAKING A LIST OF STUDENT GROUPS          |");
        System.out.println("|                2-FILLING A LIST WITH STUDENTS             |");
        System.out.println("|                3-EXIT                                     |");
        System.out.println("╰-----------------------------------------------------------╯");
    }
}
