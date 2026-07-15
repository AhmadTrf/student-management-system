package de.hs.student;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Starts the Student Management System demonstration. */
public final class App {

  private static final Logger LOGGER = LogManager.getLogger(App.class);

  private App() {
    // Prevent instantiation of the application class.
  }

  /**
   * Starts the application.
   *
   * @param args command-line arguments; currently not used
   */
  public static void main(String[] args) {
    StudentRepository repository = new StudentRepository();
    StudentService studentService = new StudentService(repository);
    StudentJsonStorage storage = new StudentJsonStorage();

    try {
      Student student =
          new Student(
              "1",
              "Ahmad",
              "Trefi",
              "trefiahm@hs-albsig.de");

      studentService.addStudent(student);

      studentService.addGradeToStudent(
          "1",
          new Grade("Java Development", 1.7));

      studentService.addGradeToStudent(
          "1",
          new Grade("Software Engineering", 2.3));

      StudentReportService reportService =
          new StudentReportService(repository);

      String report = reportService.generateReport("1");
      LOGGER.info("Generated student report:\n{}", report);

      saveAndLoadStudent(storage, student);

    } catch (IllegalArgumentException | IllegalStateException exception) {
      LOGGER.error(
          "The student data could not be processed: {}",
          exception.getMessage());
    }
  }

  /**
   * Saves a student to JSON and loads the student again.
   *
   * @param storage JSON storage service
   * @param student student to save
   */
  private static void saveAndLoadStudent(
      StudentJsonStorage storage,
      Student student) {

    String filePath = "student.json";

    try {
      storage.save(student, filePath);

      Student loadedStudent = storage.load(filePath);

      LOGGER.info(
          "Student loaded successfully: {}",
          loadedStudent.getFullName());

    } catch (IOException exception) {
      LOGGER.error(
          "The JSON file could not be saved or loaded: {}",
          exception.getMessage());
    }
  }
}