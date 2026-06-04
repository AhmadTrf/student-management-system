package de.hs.student;

public class App {

    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();
        StudentService studentService = new StudentService(repository);
        StudentReportService reportService = new StudentReportService(repository);

        Student student = new Student(
                "1",
                "Ahmad",
                "Trefi",
                "trefiahm@hs-albsig.de"
        );

        studentService.addStudent(student);
        studentService.addGradeToStudent("1", new Grade("Java Development", 1.7));
        studentService.addGradeToStudent("1", new Grade("Software Engineering", 2.3));

        System.out.println(reportService.generateReport("1"));
    }
}