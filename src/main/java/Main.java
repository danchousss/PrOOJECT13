import java.util.*;
import java.sql.*;
public class Main {
    public static void main(String [] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Scanner scanner=new Scanner(System.in);
            try{
                connection = MyJDBC.getConnection();
                System.out.println("╭-----------------------------------------------------------╮");
                System.out.println("|       WELCOME,PLEASE WRITE LOGIN TO ENTER                 |");
                System.out.println("╰-----------------------------------------------------------╯");
                String username= scanner.nextLine();
                System.out.println("╭-----------------------------------------------------------╮");
                System.out.println("|       WELCOME,PLEASE WRITE PASSWORD  TO ENTER             |");
                System.out.println("╰-----------------------------------------------------------╯");
                String password=scanner.nextLine();
                String query = "SELECT username, password, role FROM users WHERE username = ? AND password = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1 , username);
                preparedStatement.setString(2 , password);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    String role = resultSet.getString("role");
                    System.out.println("Login successful");
                    if(role.equals("Teacher")) {
                        Teacher teacher = new Teacher(username , password);
                        boolean isTrue1=true;
                        while(isTrue1){
                        teacher.menu();
                        int choiceOfTeacher= scanner.nextInt();
                        if(choiceOfTeacher==1){
                            teacher.addingPracticeTask();

                        }else if(choiceOfTeacher==2){
                            teacher.addingLaboratoryTask();
                        }else if(choiceOfTeacher==3){
                            teacher.GradingStudent();

                        }else if(choiceOfTeacher==4){
                            teacher.GradingStudent();

                        }
                        else if(choiceOfTeacher==5){
                            System.out.println("╭--------------------------------------------------╮");
                            System.out.println("|              \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                            System.out.println("╰--------------------------------------------------╯");
                            isTrue1=false;
                        }
                        }
                    }
                    if(role.equals("Student")) {
                        Student student = new Student(username, password);
                        System.out.println("Login successful");
                        boolean isTrue2=true;
                        while(isTrue2){
                        student.menu();
                        int choiceOfStudent = scanner.nextInt();
                        if(choiceOfStudent==1){
                            student.viewingPracticeTasks();
                        }else if(choiceOfStudent==2){
                            student.viewingLaboratoryTasks();
                        }else if(choiceOfStudent==3){

                        }
                        else if(choiceOfStudent==4){
                            student.viewingGrades();

                        }else if(choiceOfStudent==5){
                            System.out.println("╭--------------------------------------------------╮");
                            System.out.println("|              \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                            System.out.println("╰--------------------------------------------------╯");
                            isTrue2=false;
                        }
                    }
                    }
                    if(role.equals("SystemAdministrator")) {
                        SystemAdministrator systemAdministrator= new SystemAdministrator(username,password);
                        System.out.println("Login successful");
                        boolean isTrue1=true;
                        while(isTrue1){
                        systemAdministrator.menu();
                        int choiceOfSystemAdministrator= scanner.nextInt();
                        if(choiceOfSystemAdministrator==1){

                        }else if(choiceOfSystemAdministrator==2){

                        }else if(choiceOfSystemAdministrator==3){

                        }else if(choiceOfSystemAdministrator==4){
                            System.out.println("╭--------------------------------------------------╮");
                            System.out.println("|              \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                            System.out.println("╰--------------------------------------------------╯");
                            isTrue1=false;
                        }
                        }
                    }
                    if(role.equals("CuratorOfPractice")) {
                        CuratorOfPractice curatorOfPractice = new CuratorOfPractice(username ,password);
                        System.out.println("Login successful");
                        boolean isTrue1=true;
                        while(isTrue1){
                        curatorOfPractice.menu();
                        int choiceOfCuratorOfPractice= scanner.nextInt();
                        if(choiceOfCuratorOfPractice==1){
                        }else if(choiceOfCuratorOfPractice==2){

                        }else if(choiceOfCuratorOfPractice==7){
                            System.out.println("╭--------------------------------------------------╮");
                            System.out.println("|              \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                            System.out.println("╰--------------------------------------------------╯");
                            isTrue1=false;
                        }
                        }
                    }
                    }else{
                    System.out.println("╭--------------------------------------------------╮");
                    System.out.println("|                 ❌  TRY AGAIN                    |");
                    System.out.println("╰--------------------------------------------------╯");
                }
            }catch (SQLException e){
                System.out.println("Error connecting to the database");
            }
    }
}
//            int d= scanner.nextInt();
//            String fi=scanner.nextLine();
//            Connection connection = DriverManager.getConnection(url, username, password);
//            try(Connection connection1=DriverManager.getConnection(url, username, password);Statement statement=connection1.createStatement()){
//                statement.execute("INSERT INTO groupsofstudents (ID,Group1) VALUES (d,'fi');");
//
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//            try {
//                String query = "SELECT * FROM groupsofstudents";
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
//                ResultSet resultSet = preparedStatement.executeQuery();
//                System.out.println("Ordered products: ");
//                System.out.println("+------------+--------------------+----------------+-----------+");
//                System.out.println("| Product Id | Name               | Price          | Quantity  |");
//                System.out.println("+------------+--------------------+----------------+-----------+");
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("ID");
//                    String Group1 = resultSet.getString("Group1");
//                    System.out.printf("|%-12s|%-20s|\n",id,Group1);
//                    System.out.println("+------------+--------------------+----------------+-----------+");
//                }
//            }catch (SQLException e) {
//                e.printStackTrace();
//            }