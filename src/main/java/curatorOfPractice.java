public class curatorOfPractice extends user {
    String password="qwerty";
    String login="curator";
    public boolean verification(String password,String login){
        if(this.password.equals(password) && this.login.equals(login)){
            return true;
        }else{
            return false;
        }
    }
    public void menu(){
        System.out.println("CHOOSE AN OPTION ,YOU WANT TO DO" +
                "1-CREATING PROFILE" +
                "2-FILLING A LIST WITH STUDENTS" +
                "3-EXIT");
    }
}
