public class Student extends user {
    String password="qwerty";
    String login="student";
    public boolean verification(String password,String login){
        if(this.password.equals(password) && this.login.equals(login)){
            return true;
        }else{
            return false;
        }
    }
    public void menu(){
        System.out.println("CHOOSE AN OPTION ,YOU WANT TO DO" +
                "1-VIEWING ASSESSMENT" +
                "2-COMPLETING TASKS" +
                "3-VIEWING GRADES" +
                "4-EXIT");
    }
}
