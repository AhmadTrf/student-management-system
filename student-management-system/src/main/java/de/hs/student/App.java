package de.hs.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Demonstrates the core functionality of the student management system. */
public class App {

  private static final Logger LOGGER = LogManager.getLogger(App.class);

  /**
   * Creates a student, adds grades, and writes the generated report to the application log.
   *
   * @param args command-line arguments; currently not used
   */
  public static void main(String[] args) {
    StudentRepository repository = new StudentRepository();
    StudentService studentService = new StudentService(repository);

    Student student =
        new Student("1", "Ahmad", "Trefi", "trefiahm@hs-albsig.de");

    studentService.addStudent(student);
    studentService.addGradeToStudent(
        "1", new Grade("Java Development", 1.7));
    studentService.addGradeToStudent(
        "1", new Grade("Software Engineering", 2.3));

    StudentReportService reportService =
        new StudentReportService(repository);

    String report = reportService.generateReport("1");
    LOGGER.info("Generated student report:\n{}", report);
  }
}