package de.hs.student;

/**
 * Demo application for the student management system.
 */
public class App {

  /**
   * Starts the demo application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    StudentRepository repository = new StudentRepository();
    StudentService studentService = new StudentService(repository);

    Student student = new Student(
        "1",
        "Ahmad",
        "Trefi",
        "trefiahm@hs-albsig.de"
    );

    studentService.addStudent(student);
    studentService.addGradeToStudent("1", new Grade("Java Development", 1.7));
    studentService.addGradeToStudent("1", new Grade("Software Engineering", 2.3));
    
    StudentReportService reportService = new StudentReportService(repository);
    System.out.println(reportService.generateReport("1"));
  }
}