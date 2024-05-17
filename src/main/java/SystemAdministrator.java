import java.sql.*;
public class SystemAdministrator extends User {
    public SystemAdministrator(String username, String password) {
        super(username, password);
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
