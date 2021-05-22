import java.util.HashMap;

public class Sample {
    private int id;

    public Sample() {
    }

    public Sample(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private HashMap<Integer, Student> students;

    public Sample(int id, HashMap<Integer, Student> students) {
        this.id = id;
        this.students = students;
    }

    public HashMap<Integer, Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "id=" + id +
                ", students=" + students +
                '}';
    }

    public void setStudents(HashMap<Integer, Student> students) {
        this.students = students;
    }
}
