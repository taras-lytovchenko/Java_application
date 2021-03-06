import java.util.ArrayList;
import java.util.List;

//Напишите минимальный класс студент, имеющий: идентификатор студента (подразумевается уникальный), имя, фамилию.
//        Напишите класс, репрезентирующий группу студентов со следующими полями:
//        — староста (тип студент)
//        — список студентов (тип студент)
//        — список заданий
//        Группа не может существовать без старосты
//        Методы класса:
//        — сменить старосту
//        — добавить/удалить/переименовать студента,
//        — добавить задание (для всей группы)
//        — обозначить задание как выполненное (для указанного студента)
//        Староста и студенты являются объектами класса студент.
//        Задания являются простыми строками (пример: “изучить инкапсуляцию”).
//        Сделайте данный класс максимально инкапсулированным.
//        Обеспечьте максимальную безопасность состояния класса.
public class StudentsGroup {
    private List<Student> studentsList;
    private List<String> tasks;
    private Student captain;

    private static final String COMPLETED = "COMPLETED";

    public StudentsGroup(Student captain, List<Student> studentList, ArrayList<String> tasks) {
        this.captain = captain;
        this.studentsList = studentList;
        this.tasks = new ArrayList<>();
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public Student getCaptain() {
        return captain;
    }

    public void setCaptain(Student captain) {
        this.captain = captain;
    }

    public void changeCaptain(String name, String lastName) {
        List<Student> studentsList = this.getStudentsList();

        studentsList.stream()
                .filter(student -> name.equals(student.getName()))
                .filter(student -> lastName.equals(student.getLastname()))
                .findAny().ifPresent(this::setCaptain);
    }
//    По логике этот метод должен устанавливать старосту только для своего екземпляра, тут studentsGroup входящий параметр лишний
//
//    Ну и для установки старосты полагаться на имя студента даже без фамилии как-то не очень надежно

    public String deleteStudent(String name) {
        List<Student> studentsList = this.getStudentsList();

        studentsList.stream()
                .filter(student -> name.equals(student.getName()))
                .findAny().ifPresent(studentsList::remove);
        return name;
    }

    public void addStudent(int id, String firstName, String lastName) {
        List<Student> studentsList = this.getStudentsList();

        Student student = new Student(id, firstName, lastName);

        studentsList.add(student);
    }

    public void renameStudent(int id, String name) {
        List<Student> studentsList = this.getStudentsList();

        Student studentForRename = studentsList.stream()
                .filter(student -> id == (int) student.getStudentId())
                .findAny()
                .orElse(null);

        if (studentForRename != null) {
            int studentForRenameIndex = studentsList.indexOf(studentForRename);
            if (studentForRenameIndex != 0) {
                studentForRename.setName(name);
                studentsList.set(studentForRenameIndex, studentForRename);
            }
        }
    }

    public void addTasks(String taskName) {
        List<String> taskList = this.getTasks();
        taskList.add(taskName);
    }

    public void addTaskStatusForStudent(String name, String taskName) {
        List<Student> studentsList = this.getStudentsList();
        List<String> taskList = this.getTasks();

        String taskForUpdate = taskList.stream()
                .filter(taskName::equals)
                .findAny()
                .orElse(null);

        if (taskForUpdate != null) {
            Student studentForTaskUpdate = studentsList.stream()
                    .filter(student -> name.equals(student.getName()))
                    .findAny()
                    .orElse(null);
            if (studentForTaskUpdate != null) {
                int taskForUpdateIndex = taskList.indexOf(taskForUpdate);
                taskForUpdate += " was done by next students: " + studentForTaskUpdate.getName() + " with status " + COMPLETED;
                taskList.set(taskForUpdateIndex, taskForUpdate);
            }
        }
    }

}