import java.util.Scanner;

public class UserInterface extends CRUDoperation{
    public void menu(){
        System.out.println("========================");
        System.out.println("Welcome");
        Student student = new Student();
        while(true){
            System.out.println("Please select operation");
            System.out.println("1) add student");
            System.out.println("2) show students");
            System.out.println("3) update student");
            System.out.println("4) delete student");
            System.out.println("5) search student");
            System.out.println("0) exit menu");
            Scanner input = new Scanner(System.in);
            String number = input.nextLine();
            if (control(number)){
                switch (Integer.parseInt(number)){
                    case 1:
                        createStudent();
                        break;
                    case 2:
                        showAllStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        searchStudent();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("wrong input");
                        break;
                }
            }
            else {
                menu();
            }
        }
    }
}
