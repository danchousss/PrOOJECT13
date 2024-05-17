import java.sql.*;
public class Student extends User {
    public Student(String username, String password) {
        super(username, password);
    }
    public  void viewingPracticeTasks(){
        try {
            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT PracticeTasks FROM tasks1");
            while (resultSet.next()) {
                String practiceTask = resultSet.getString("PracticeTasks");
                System.out.println("Practice Task: " + practiceTask);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public  void viewingLaboratoryTasks(){
        try {
            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LaboratoryTasks FROM tasks1");
            while (resultSet.next()) {
                String laboratoryTasks = resultSet.getString("LaboratoryTasks");
                System.out.println("LaboratoryTasks: " + laboratoryTasks);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void viewingGrades(){
        try {
            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT GradesForHomeworks FROM tasks1");
            while (resultSet.next()) {
                String GradesForHomeworks = resultSet.getString("GradesForHomeworks");
                System.out.println("GradesForHomeworks: " + GradesForHomeworks);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void menu(){
        System.out.println("╭-----------------------------------------------------------╮");
        System.out.println("|                CHOOSE AN OPTION ,YOU WANT TO DO           |");
        System.out.println("|                1-VIEWING PRACTICE TASKS                   |");
        System.out.println("|                2-VIEWING LABORATORY TASKS                 |");
        System.out.println("|                3-COMPLETING TASKS                         |");
        System.out.println("|                4-VIEWING GRADES                           |");
        System.out.println("|                5-EXIT                                     |");
        System.out.println("╰-----------------------------------------------------------╯");
    }
}
