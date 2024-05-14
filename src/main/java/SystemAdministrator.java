public class SystemAdministrator extends user {
    String password="qwerty";
    String login="admin";
    public boolean verification(String password,String login){
        if(password.equals(this.password) &&login.equals(this.login)){
            return true;
        }else{
            return false;
        }
    }
    public void menu(){
        System.out.println("CHOOSE AN OPTION ,YOU WANT TO DO" +
                "1-MAKING A LIST OF STUDENT GROUPS" +
                "2-FILLING A LIST WITH STUDENTS" +
                "3-EXIT");
    }
}
