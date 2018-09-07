package lesson8.students;

public class Demo {
    static Course[] courses = new Course[1];

    public static void main(String[] args) {
        createHighestParent();
        createLowestChild();
    }

    static Student createHighestParent() {
        Student student = new Student("Joe", "Brown", 1, courses);
        return student;
    }

    static SpecialStudent createLowestChild() {
        SpecialStudent specialStudent = new SpecialStudent("Joe", "Brown", 1, courses, 999, "ex@ex.com");
        return specialStudent;
    }
}
