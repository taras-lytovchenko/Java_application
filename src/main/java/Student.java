public class Student {
    private int studentId;
    private String name;
    private String Lastname;

    public Student(int studentId, String name, String Lastname) {
        this.studentId = studentId;
        this.name = name;
        this.Lastname = Lastname;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }
}
