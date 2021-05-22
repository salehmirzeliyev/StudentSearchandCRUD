import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.lang.model.element.NestingKind;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CRUDoperation extends Student{
    Student student  = new Student();

    public static List<Student> allStudents = new ArrayList<>();
    public  static HashMap<Integer,Student> allStudentsHashMapID = new HashMap<>();
    public  static HashMap<String,Student> allStudentsHashMapName = new HashMap<>();
    public  static HashMap<String,Student> allStudentsHashMapSurName = new HashMap<>();
    public  static HashMap<String,Student> allStudentsHashMapFather = new HashMap<>();

    public void createStudent(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name:");
        String name = input.nextLine();
        System.out.println("Enter surname:");
        String surname = input.nextLine();
        System.out.println("Enter fathername:");
        String fathername = input.nextLine();
        System.out.println("Enter email");
        String email = input.nextLine();
        System.out.println("Enter phone number");
        String phonenumber = input.nextLine();
        Student newStudent = new Student(idGenerator(),name,surname,fathername,email,phonenumber);
        allStudentsHashMapName.put((newStudent.getName()+newStudent.getId()), newStudent);
        allStudentsHashMapSurName.put((newStudent.getUsername()+newStudent.getId()), newStudent);
        allStudentsHashMapFather.put((newStudent.getFathername()+newStudent.getId()),newStudent);
        allStudentsHashMapID.put((newStudent.getId()),newStudent);
        addJson();
    }
    public void showAllStudents(){
        for (Student std: allStudentsHashMapID.values()){
            System.out.println(std.toString());
        }
    }
    public void showStudent(Student std){
        System.out.println(std.toString());
    }

    public void updateStudent() {
        System.out.println("Search with id");
        System.out.println("please input student id");
        Scanner input = new Scanner(System.in);
        String id = input.next();
        if (!control(id)) {
            System.out.println("not found");
            updateStudent();
        }
        Student std = allStudentsHashMapID.get(Integer.parseInt(id));
        if (std != null) {
            showStudent(std);
            System.out.println("enter new name:");
            String newname = input.next();
            std.setName(newname);
            System.out.println("enter new surname: ");
            String newsurname = input.next();
            std.setUsername(newsurname);
            System.out.println("enter new father name: ");
            String newfathername = input.next();
            std.setFathername(newfathername);
            System.out.println("enter new email: ");
            String newemail = input.next();
            std.setEmail(newemail);
            System.out.println("enter new phone number: ");
            String newphonenumber = input.next();
            setPhonenumber(newphonenumber);
            addJson();
        } else {
            System.out.println("not found this id");
            updateStudent();
        }
    }

    public void deleteStudent() {
        System.out.println("You can remove student");
        System.out.println("enter student id: ");
        Scanner input = new Scanner(System.in);
        String deleteID = input.next();
        if (!control(deleteID)) {
            System.out.println("not found this id");
            deleteStudent();
        }
        Student deleteStudentID = allStudentsHashMapID.get(Integer.parseInt(deleteID));
        if (deleteStudentID != null) {
            allStudentsHashMapID.remove(Integer.parseInt(deleteID));
            addJson();
        } else {
            System.out.println("not found this id");
            deleteStudent();
        }
    }
    public void jsonToObject() throws IOException {
        Gson gson = new Gson();
        Type classroomType = new TypeToken<Sample>(){}.getType();
        Sample sample = gson.fromJson(new FileReader("studentJson.json"), classroomType);
        HashMap<Integer, Student> students = sample.getStudents();
        for (Student student : students.values()) {
            allStudentsHashMapID.put(student.getId(), student);
            allStudentsHashMapName.put((student.getName() + student.getId()), student);
            allStudentsHashMapSurName.put((student.getUsername() + student.getId()), student);
            allStudentsHashMapFather.put((student.getFathername() + student.getId()), student);
        }
    }

    public void addJson() {
        Sample sample = new Sample();
        sample.setStudents(allStudentsHashMapID);
        allStudentsHashMapName.clear();
        allStudentsHashMapSurName.clear();
        allStudentsHashMapFather.clear();
        for (Student std : allStudentsHashMapID.values()) {
            allStudentsHashMapName.put((std.getName() + std.getId()), std);
            allStudentsHashMapSurName.put((std.getUsername() + std.getId()), std);
            allStudentsHashMapFather.put((std.getFathername() + std.getId()), std);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try
                (FileWriter writer = new FileWriter("studentJson.json")) {
            gson.toJson(sample, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean control(String input) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(input);
        if (input.length() < 9 && matcher.matches()==true){
            return true;
    }
        return false;
        }

        public int idGenerator() {
            int id = (int)(Math.random()*((1000000-10)+10));
            boolean yes = allStudentsHashMapID.containsKey(id);
            if (yes) {
                idGenerator();
            }
            return id;
        }

        public void searchStudent(){
            System.out.println("You can search by:");
            System.out.println("1) id");
            System.out.println("2) name");
            System.out.println("3) father name");
            System.out.println("4) surname");
            Scanner input = new Scanner(System.in);
            String something = input.nextLine();
            if (control(something)){
                int number = Integer.parseInt(something);
                switch (number){
                    case 1:
                        System.out.println("you can search by id, please enter id: ");
                        String id = input.nextLine();
                        if (control(id)){
                            Student std = allStudentsHashMapID.get(Integer.parseInt(id));
                            if (std != null){
                                showStudent(std);
                            } else {
                                System.out.println("not fount this id");
                                searchStudent();
                            }
                        }
                        break;
                    case 2:
                        System.out.println("you can search by name, please enter name: ");
                        String name = input.nextLine();
                        Stream<Map.Entry<String, Student>> std = allStudentsHashMapName.entrySet()
                                .stream().filter(i -> i.getKey().toLowerCase().startsWith(name.toLowerCase()));
                        long n = allStudentsHashMapName.keySet().stream().filter(i -> i.toLowerCase()
                        .startsWith(name.toLowerCase())).count();
                        getStudentForSearching(std, n);
                        break;
                    case 3:
                        System.out.println("you can search by father name, please enter it: ");
                        String fn = input.nextLine();
                        Stream<Map.Entry<String, Student>> stdfn = allStudentsHashMapFather.entrySet()
                                .stream().filter(i -> i.getKey().toLowerCase().startsWith(fn.toLowerCase()));
                        long m = allStudentsHashMapFather.keySet().stream().filter(i -> i.toLowerCase()
                        .startsWith(fn.toLowerCase())).count();
                        getStudentForSearching(stdfn, m);
                        break;
                    case 4:
                        System.out.println("you can search by surname, please enter surname: ");
                        String surname = input.nextLine();
                        Stream<Map.Entry<String, Student>> sstd = allStudentsHashMapSurName.entrySet()
                                .stream().filter(i -> i.getKey().toLowerCase().startsWith(surname.toLowerCase()));
                        long t = allStudentsHashMapSurName.keySet().stream().filter(i -> i.toLowerCase()
                                .startsWith(surname.toLowerCase())).count();
                        getStudentForSearching(sstd, t);
                        break;
                    default:
                        System.out.println("please try again");
                        break;
                }
            }
        }

        public void getStudentForSearching(Stream<Map.Entry<String,Student>> r, long n){
        if (n != 0){
            for (Map.Entry<String ,Student> stringStudentEntry: r.collect(Collectors.toList())){
                System.out.println(stringStudentEntry.getValue().toString());
            }
        }
        else{
            System.out.println("not fount");
        }
        }
}



