import java.io.IOException;

public class MainMenu {
    public static void main(String[] args) throws IOException {
        Student student = new Student();
        CRUDoperation cruDoperation = new CRUDoperation();
        UserInterface userInterface = new UserInterface();
        cruDoperation.jsonToObject();
        userInterface.menu();
    }
}
