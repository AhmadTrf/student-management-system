package de.hs.student;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StudentJsonStorageTest {

  private static final double DELTA = 0.0001;

  @TempDir
  Path tempDirectory;

  @Test
  void savesAndLoadsStudent() throws IOException {
    Student student =
        new Student(
            "1",
            "Ahmad",
            "Trefi",
            "trefiahm@hs-albsig.de");

    student.addGrade(new Grade("Java Development", 1.7));

    Path jsonFile = tempDirectory.resolve("student.json");
    StudentJsonStorage storage = new StudentJsonStorage();

    storage.save(student, jsonFile.toString());

    Student loadedStudent = storage.load(jsonFile.toString());

    assertAll(
        () -> assertEquals(student.getId(), loadedStudent.getId()),
        () -> assertEquals(
            student.getFullName(),
            loadedStudent.getFullName()),
        () -> assertEquals(1, loadedStudent.getGrades().size()),
        () -> assertEquals(
            "Java Development",
            loadedStudent.getGrades().get(0).getCourseName()),
        () -> assertEquals(
            1.7,
            loadedStudent.getGrades().get(0).getValue(),
            DELTA));
  }

  @Test
  void rejectsMissingJsonFile() {
    Path missingFile = tempDirectory.resolve("missing.json");
    StudentJsonStorage storage = new StudentJsonStorage();

    assertThrows(
        IOException.class,
        () -> storage.load(missingFile.toString()));
  }
}