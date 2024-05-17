public class CuratorOfPractice extends User {
    public CuratorOfPractice(String username, String password) {
        super(username, password);
    }
    public void menu(){
        System.out.println("╭-----------------------------------------------------------╮");
        System.out.println("|                CHOOSE AN OPTION ,YOU WANT TO DO           |");
        System.out.println("|                1-CREATING PROFILE                         |");
        System.out.println("|                2-DELETING PROFILE                         |");
        System.out.println("|                3-EDITING PROFILE                           |");
        System.out.println("|                4-CREATING COURSE                          |");
        System.out.println("|                5-DELETING COURSE                          |");
        System.out.println("|                6-EDITING COURSE                           |");
        System.out.println("|                7-EXIT                                     |");
        System.out.println("╰-----------------------------------------------------------╯");
    }
}
