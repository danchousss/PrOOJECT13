import java.util.*;
public class Teacher extends user {
    Scanner scanner=new Scanner(System.in);
    String password="qwerty";
    String login="teacher";
    public boolean verification(String password,String login){
        if(this.password.equals(password) && this.login.equals(login)){
            return true;
        }else{
            return false;
        }
    }
    public void addingTask(){
        System.out.println("ENTER YOUR TASK");
        String task=scanner.nextLine();
    }
    public void menu(){
        System.out.println("CHOOSE AN OPTION ,YOU WANT TO DO" +
                "1-ADDING AND PUBLISHING TASKS" +
                "2-ASSESSMENT OF TASKS" +
                "3-PROVIDING FEEDBACK" +
                "4-EXIT");
    }
}
