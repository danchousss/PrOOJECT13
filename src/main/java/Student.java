import java.sql.*;
import java.util.*;

public class Student extends User {
    Scanner scanner=new Scanner(System.in);

    public Student(String username, String password) {

        super(username, password);

    }
    public void viewingTasks(){
        System.out.println("What kind of task you wanna view");
        System.out.println("PRACTICE-1");
        System.out.println("LABORATORY-2");
        int choice= scanner.nextInt();
        scanner.nextLine();

        if(choice==1){
            viewingPracticeTasks();

        }else if(choice==2){
            viewingLaboratoryTasks();

        }else{
            System.out.println("INCORRECT");
        }
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
    public  void viewingGrades() {
        try {
            Connection connection = MyJDBC.getConnection();
            //sdgit

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT ID, StudentsHomeworksForPractice, GradeForPractice, StudentsHomeworkForLaboratory, GradeForLaboratoryTasks FROM tasks1");

            System.out.println("╭-------------------------------------------------------------------------------------------------------------------------╮");
            System.out.printf("|%-10s| %-30s| %-20s| %-30s| %-20s|%n", "ID", "Students Homework For Practice", "Grade For Practice", "Students Homework For Laboratory", "Grade For Laboratory Tasks");
            System.out.println("|-------------------------------------------------------------------------------------------------------------------------|");


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String studentsHomeworksForPractice = resultSet.getString("StudentsHomeworksForPractice");
                String gradeForPractice = resultSet.getString("GradeForPractice");
                String studentsHomeworkForLaboratory = resultSet.getString("StudentsHomeworkForLaboratory");
                String gradeForLaboratoryTasks = resultSet.getString("GradeForLaboratoryTasks");

                System.out.printf("|%-10d| %-30s| %-20s| %-30s| %-20s|%n", id, studentsHomeworksForPractice, gradeForPractice, studentsHomeworkForLaboratory, gradeForLaboratoryTasks);
            }

            System.out.println("╰-------------------------------------------------------------------------------------------------------------------------╯");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void CompletingTasks(){
        try {
            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID, PracticeTasks, LaboratoryTasks FROM tasks1");
            System.out.println("╭-----------------------------------------------------------╮");

            System.out.printf("|%-10s %-20s %-30s%n", "ID", "Practice Task", "LabortoryTasks|");
            System.out.println("|------------------------------------------------------------|");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String practiceTask = resultSet.getString("PracticeTasks");
                String laboratoryTasks = resultSet.getString("LaboratoryTasks");
                System.out.printf("|%-10d %-20s %-30s%n", id, practiceTask, laboratoryTasks,"|");
            }
            System.out.println("╰------------------------------------------------------------╯");
            System.out.println("CHOOSE WHAT KIND OF TASK YOU WANNA DO");
            System.out.println("1-PRACTICE");
            System.out.println("2-LABORATORY");
            int choice=scanner.nextInt();
            if(choice == 1){
                try {
                    Connection connection1 = MyJDBC.getConnection();
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("ENTER ID: ");
                    int id = scanner.nextInt();
                    System.out.print("ENTER YOUR HOMEWORK OF PRACTICE TASKS: ");
                    String homework = scanner.next();
                    String sql = "UPDATE tasks1 SET StudentsHomeworksForPractice = ? WHERE ID = ?";
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, homework);
                    pstmt.setInt(2, id);
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("HOMEWORK UPDATED SUCCESSFULLY");
                    } else {
                        System.out.println("ID NOT FOUND");
                    }
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }else if(choice==2){
                try {
                    Connection connection1 = MyJDBC.getConnection();
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("ENTER ID: ");
                    int id = scanner.nextInt();
                    System.out.print("ENTER YOUR HOMEWORK OF LABORATORY TASKS: ");
                    String homework = scanner.next();
                    String sql = "UPDATE tasks1 SET StudentsHomeworkForLaboratory = ? WHERE ID = ?";
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, homework);
                    pstmt.setInt(2, id);
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("HOMEWORK UPDATED SUCCESSFULLY");
                    } else {
                        System.out.println("ID NOT FOUND");
                    }
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void menu(){
        System.out.println("╭-----------------------------------------------------------╮");
        System.out.println("|                CHOOSE AN OPTION ,YOU WANT TO DO           |");
        System.out.println("|                1-VIEWING TASKS                            |");
        System.out.println("|                2-COMPLETING TASKS                         |");
        System.out.println("|                3-VIEWING GRADES                           |");
        System.out.println("|                4-EXIT                                     |");
        System.out.println("╰-----------------------------------------------------------╯");
    }
}

