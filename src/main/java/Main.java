import java.util.*;
import java.sql.*;
public class Main {
    public static void main(String [] args) {
        Scanner scanner=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean bool=true;
        boolean bool2=false;
        int count=0;
//        while(count<=2 || bool2==false){
        while(count<2) {
            try {
                connection = MyJDBC.getConnection();
                System.out.println("╭-----------------------------------------------------------╮");
                System.out.println("|       WELCOME,PLEASE WRITE LOGIN TO ENTER                 |");
                System.out.println("╰-----------------------------------------------------------╯");
                String username = scanner.nextLine();
                System.out.println("╭-----------------------------------------------------------╮");
                System.out.println("|       WELCOME,PLEASE WRITE PASSWORD  TO ENTER             |");
                System.out.println("╰-----------------------------------------------------------╯");
                String password = scanner.nextLine();
                String query = "SELECT username, password, role FROM users WHERE username = ? AND password = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String role = resultSet.getString("role");
                    if (role.equals("Teacher")) {
                        bool=false;
                        Teacher teacher = new Teacher(username, password);
                        boolean isTrue1 = true;
                        while (isTrue1) {
                            teacher.menu();
                            int choiceOfTeacher = scanner.nextInt();
                            scanner.nextLine();
                            if (choiceOfTeacher == 1) {

                                teacher.addingATask();
                            }  else if (choiceOfTeacher == 2) {
                                teacher.GradingStudent();
                            } else if (choiceOfTeacher == 3) {
                                teacher.ProvidingFeedback();
                            } else if (choiceOfTeacher == 4) {
                                System.out.println("╭--------------------------------------------------╮");
                                System.out.println("|              \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                                System.out.println("╰--------------------------------------------------╯");
                                isTrue1 = false;
                                count++;

                            }
                        }
                    }
                    if (role.equals("Student")) {
                        bool=false;
                        Student student = new Student(username, password);
                        boolean isTrue2 = true;
                        while (isTrue2) {
                            student.menu();
                            int choiceOfStudent = scanner.nextInt();
                            scanner.nextLine();

                            if (choiceOfStudent == 1) {
                                student.viewingTasks();
                            } else if (choiceOfStudent == 2) {
                                student.CompletingTasks();
                            } else if (choiceOfStudent == 3) {
                                student.viewingGrades();
                            } else if (choiceOfStudent == 4) {
                                System.out.println("╭--------------------------------------------------╮");
                                System.out.println("|              \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                                System.out.println("╰--------------------------------------------------╯");
                                isTrue2 = false;
                                count++;

                            }
                        }
                    }
                    if (role.equals("SystemAdministrator")) {
                        bool=false;
                        SystemAdministrator systemAdministrator = new SystemAdministrator(username, password);
                        boolean isTrue1 = true;
                        while (isTrue1) {
                            systemAdministrator.menu();
                            int choiceOfSystemAdministrator = scanner.nextInt();


                            if (choiceOfSystemAdministrator == 1) {
                                systemAdministrator.AddingNewProfile();
                            } else if (choiceOfSystemAdministrator == 2) {
                                systemAdministrator.deletingProfile();
                            } else if (choiceOfSystemAdministrator == 3) {
                                systemAdministrator.EditingProfile();
                            } else if (choiceOfSystemAdministrator == 4) {
                                systemAdministrator.CreatingCourse();
                            } else if (choiceOfSystemAdministrator == 5) {
                                systemAdministrator.deletingCourse();
                            } else if (choiceOfSystemAdministrator == 6) {
                                systemAdministrator.editingCourse();
                            } else if (choiceOfSystemAdministrator == 7) {
                                System.out.println("╭--------------------------------------------------╮");
                                System.out.println("|              \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                                System.out.println("╰--------------------------------------------------╯");
                                isTrue1 = false;
                                count++;

                            }
                        }
                    }
                    if (role.equals("CuratorOfPractice")) {
                        bool = false;

                        CuratorOfPractice curatorOfPractice = new CuratorOfPractice(username, password);
                        boolean isTrue1 = true;
                        while (isTrue1) {
                            curatorOfPractice.menu();
                            int choiceOfCuratorOfPractice = scanner.nextInt();
                            scanner.nextLine();

                            if (choiceOfCuratorOfPractice == 1) {
                                curatorOfPractice.makingAListOfStudents();
                            } else if (choiceOfCuratorOfPractice == 2) {
                                curatorOfPractice.FillingAList();
                            } else if (choiceOfCuratorOfPractice == 3) {
                                System.out.println("╭--------------------------------------------------╮");
                                System.out.println("|              \uD83D\uDD90THANK YOU,GOOD BYE                 |");
                                System.out.println("╰--------------------------------------------------╯");
                                isTrue1 = false;
                                count++;
                            }
                        }
                    }
                } else {
                    System.out.println("╭--------------------------------------------------╮");
                    System.out.println("|                 ❌  TRY AGAIN                    |");
                    System.out.println("╰--------------------------------------------------╯");
                }
            } catch (SQLException e) {
                System.out.println("Error connecting to the database");
            }
        }
        }
    }

