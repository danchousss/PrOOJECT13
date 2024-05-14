import java.util.*;
import java.sql.*;
public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/products";
    //fiv
    private static final String username = "root";

    private static final String password = "Dzharkynbaev27";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Scanner scanner=new Scanner(System.in);

            Connection connection = DriverManager.getConnection(url, username, password);

            Teacher teacher=new Teacher();
            Student student=new Student();
            SystemAdministrator systemAdministrator=new SystemAdministrator();
            curatorOfPractice curatorOfPractice=new curatorOfPractice();
            System.out.println("╭-----------------------------------------------------------╮");
            System.out.println("|       WELCOME,PLEASE WRITE PASSWORD AND LOGIN TO ENTER    |");
            System.out.println("╰-----------------------------------------------------------╯");
            boolean isTrue=true;
            while (isTrue){
                String password=scanner.nextLine();
                String login= scanner.nextLine();
                if(teacher.verification(password,login)){
                    teacher.menu();
                    int choiceOfTeacher= scanner.nextInt();
                    if(choiceOfTeacher==1){


                    }else if(choiceOfTeacher==2){

                    }else if(choiceOfTeacher==3){

                    }else if(choiceOfTeacher==4){
                        System.out.println("╭--------------------------------------------------╮");
                        System.out.println("|   \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                        System.out.println("╰--------------------------------------------------╯");
                    }
                    isTrue=false;
                }else if(student.verification(password,login)){
                    student.menu();
                    int choiceOfStudent = scanner.nextInt();
                    if(choiceOfStudent==1){

                    }else if(choiceOfStudent==2){

                    }else if(choiceOfStudent==3){
                        System.out.println("╭--------------------------------------------------╮");
                        System.out.println("|   \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                        System.out.println("╰--------------------------------------------------╯");
                    }
                    isTrue=false;
                } else if (systemAdministrator.verification(password,login)) {
                    systemAdministrator.menu();
                    int choiceOfSystemAdministrator= scanner.nextInt();
                    if(choiceOfSystemAdministrator==1){

                    }else if(choiceOfSystemAdministrator==2){

                    }else if(choiceOfSystemAdministrator==3){

                    }else if(choiceOfSystemAdministrator==4){
                        System.out.println("╭--------------------------------------------------╮");
                        System.out.println("|   \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                        System.out.println("╰--------------------------------------------------╯");
                    }
                    isTrue=false;
                }else if (curatorOfPractice.verification(password,login)){
                    curatorOfPractice.menu();
                    int choiceOfCuratorOfPractice= scanner.nextInt();
                    if(choiceOfCuratorOfPractice==1){

                    }else if(choiceOfCuratorOfPractice==2){

                    }else if(choiceOfCuratorOfPractice==3){
                        System.out.println("╭--------------------------------------------------╮");
                        System.out.println("|   \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                        System.out.println("╰--------------------------------------------------╯");
                        return;
                    }
                    isTrue=false;
                }else{
                    System.out.println("╭--------------------------------------------------╮");
                    System.out.println("|                 ❌  TRY AGAIN                    |");
                    System.out.println("╰--------------------------------------------------╯");

                }
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
