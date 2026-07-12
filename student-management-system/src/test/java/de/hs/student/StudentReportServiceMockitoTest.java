package de.hs.student;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class StudentReportServiceMockitoTest {

  @Test
  void usesRepositoryToGenerateReport() {
    StudentRepository repository = mock(StudentRepository.class);

    Student student =
        new Student(
            "1",
            "Ahmad",
            "Trefi",
            "trefiahm@hs-albsig.de");

    student.addGrade(new Grade("Java Development", 1.7));

    when(repository.findById("1"))
        .thenReturn(Optional.of(student));

    StudentReportService reportService =
        new StudentReportService(repository);

    String report = reportService.generateReport("1");

    assertTrue(report.contains("Ahmad Trefi"));
    assertTrue(report.contains("Java Development"));
    assertTrue(report.contains("PASSED"));

    verify(repository).findById("1");
  }
}