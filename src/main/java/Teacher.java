import java.util.*;
import java.sql.*;

public class Teacher extends User {

    Scanner scanner=new Scanner(System.in);


    public Teacher(String username, String password) {

        super(username, password);
    }

    public void addingPracticeTask(){
        System.out.println("ENTER YOUR TASK");
        String task=scanner.nextLine();
        try {
            Connection connection = MyJDBC.getConnection();
            String sql = "INSERT INTO tasks1 (PracticeTasks) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, task);
            preparedStatement.executeUpdate();
            System.out.println("╭--------------------------------------------------╮");
            System.out.println("|         YOUR TASK WAS SUCCESFULLY ADDED          |");
            System.out.println("╰--------------------------------------------------╯");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addingLaboratoryTask(){
        System.out.println("ENTER YOUR TASK");
        String task=scanner.nextLine();
        try {
            Connection connection = MyJDBC.getConnection();
            String sql = "INSERT INTO tasks1 (LaboratoryTasks) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, task);
            preparedStatement.executeUpdate();
            System.out.println("╭--------------------------------------------------╮");
            System.out.println("|         YOUR TASK WAS SUCCESFULLY ADDED          |");
            System.out.println("╰--------------------------------------------------╯");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void GradingStudent(){
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


            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("CHOOSE WHAT KIND OF TASK YOU WANNA GRADE");
        System.out.println("1-PRACTICE");
        System.out.println("2-LABORATORY");
        int choice=scanner.nextInt();
        if(choice==1){
            try {
                Connection connection = MyJDBC.getConnection();
                Scanner scanner = new Scanner(System.in);
                System.out.print("ENTER ID: ");
                int id = scanner.nextInt();
                System.out.print("ENTER GRADE FOR PRACTICE HOMEWORK: ");
                int grade = scanner.nextInt();
                String sql = "UPDATE tasks1 SET GradeForPractice = ? WHERE ID = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, grade);
                pstmt.setInt(2, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("GRADE UPDATED SUCCESFULLY");
                } else {
                    System.out.println("ID NOT FOUND");
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else if(choice==2){
            System.out.println("CHOOSE ID OF TASK");
            int choiceofTask=scanner.nextInt();



        }


    }
    public void menu(){
        System.out.println("╭-----------------------------------------------------------╮");
        System.out.println("|                CHOOSE AN OPTION ,YOU WANT TO DO           |");
        System.out.println("|                1-ADDING AND PUBLISHING TASKS              |");
        System.out.println("|                2-ADDING LABORATORY TASKS                  |");
        System.out.println("|                3-GRADE ASSESSMENT OF TASKS                |");
        System.out.println("|                4-PROVIDING FEEDBACK                       |");
        System.out.println("|                5-EXIT                                     |");
        System.out.println("╰-----------------------------------------------------------╯");
    }
}
