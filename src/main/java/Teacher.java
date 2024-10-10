import java.util.*;
import java.sql.*;

public class Teacher extends User {

    Scanner scanner=new Scanner(System.in);



    public Teacher(String username, String password) {


        super(username, password);
    }
    public void addingATask(){
        System.out.println("What kind of task you wanna add");
        System.out.println("PRACTICE-1");
        System.out.println("LABORATORY-2");
        int choice= scanner.nextInt();
        scanner.nextLine();

        if(choice==1){
            addingPracticeTask();

        }else if(choice==2){
            addingLaboratoryTask();

        }else{
            System.out.println("INCORRECT");
        }

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
            ResultSet resultSet = statement.executeQuery("SELECT ID, StudentsHomeworksForPractice, StudentsHomeworkForLaboratory FROM tasks1");
            System.out.println("╭-----------------------------------------------------------------------------------------------╮");

            System.out.printf("|%-10s %-30s %-30s%n", "ID", "Students Homework For Practice|", "Students Homework For Laboratory|");
            System.out.println("|-----------------------------------------------------------------------------------------------|");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String practiceHomework = resultSet.getString("StudentsHomeworksForPractice");
                String laboratoryHomework = resultSet.getString("StudentsHomeworkForLaboratory");
                System.out.printf("|%-10d %-30s %-30s%n", id, practiceHomework, laboratoryHomework);
            }
            System.out.println("╰-----------------------------------------------------------------------------------------------╯");

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
            try {
                Connection connection = MyJDBC.getConnection();
                Scanner scanner = new Scanner(System.in);
                System.out.print("ENTER ID: ");
                int id = scanner.nextInt();
                System.out.print("ENTER GRADE FOR LABORATORY HOMEWORK: ");
                int grade = scanner.nextInt();
                String sql = "UPDATE tasks1 SET GradeForLaboratoryTasks = ? WHERE ID = ?";
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
        }
    }
    public void ProvidingFeedback(){
        try {
            Connection connection = MyJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID,StudentsHomeworksForPractice,StudentsHomeworkForLaboratory  FROM tasks1");
            System.out.println("╭-----------------------------------------------------------╮");

            System.out.printf("|%-10s %-20s %-30s%n", "ID", "StudentsHomeworksForPractice", "StudentsHomeworkForLaboratory|");
            System.out.println("|------------------------------------------------------------|");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String practiceTask = resultSet.getString("StudentsHomeworksForPractice");
                String laboratoryTasks = resultSet.getString("StudentsHomeworkForLaboratory");
                System.out.printf("|%-10d %-20s %-30s%n", id, practiceTask, laboratoryTasks,"|");
            }
            System.out.println("╰------------------------------------------------------------╯");


            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("CHOOSE FOR WHICH KIND OF HOMEWORK YOU WANNA GIVE A FEEDBACK");
        System.out.println("1-PRACTICE");
        System.out.println("2-LABORATORY");
        int choice=scanner.nextInt();
        if(choice==1){
            try {
                Connection connection = MyJDBC.getConnection();
                Scanner scanner = new Scanner(System.in);

                System.out.print("ENTER ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("ENTER FEEDBACK FOR PRACTICE HOMEWORK: ");
                String feedback = scanner.nextLine();

                String sql = "UPDATE tasks1 SET FeedBackForPractice = ? WHERE ID = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, feedback);
                pstmt.setInt(2, id);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("FEEDBACK UPDATED SUCCESSFULLY");
                } else {
                    System.out.println("ID NOT FOUND");
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(choice==2){
            try {
                Connection connection = MyJDBC.getConnection();
                Scanner scanner = new Scanner(System.in);

                System.out.print("ENTER ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("ENTER FEEDBACK FOR LABORATORY HOMEWORK: ");
                String feedback = scanner.nextLine();
                String sql = "UPDATE tasks1 SET FeedbackForLaboratory = ? WHERE ID = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, feedback);
                pstmt.setInt(2, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("FEEDBACK UPDATED SUCCESSFULLY");
                } else {
                    System.out.println("ID NOT FOUND");
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void menu(){
        System.out.println("╭-----------------------------------------------------------╮");
        System.out.println("|                CHOOSE AN OPTION ,YOU WANT TO DO           |");
        System.out.println("|                1-ADDING  TASKS                            |");
        System.out.println("|                2-GRADE ASSESSMENT OF TASKS                |");
        System.out.println("|                3-PROVIDING FEEDBACK                       |");
        System.out.println("|                4-EXIT                                     |");
        System.out.println("╰-----------------------------------------------------------╯");
    }
}
