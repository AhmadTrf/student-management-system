package de.hs.student;

import java.util.Optional;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentReportServiceMockitoTest extends TestCase {

    public void testGenerateReportUsesRepositoryMock() {
        StudentRepository repository = mock(StudentRepository.class);

        Student student = new Student(
                "1",
                "Ahmad",
                "Trefi",
                "trefiahm@hs-albsig.de"
        );

        student.addGrade(new Grade("Java Development", 1.7));

        when(repository.findById("1")).thenReturn(Optional.of(student));

        StudentReportService reportService = new StudentReportService(repository);

        String report = reportService.generateReport("1");

        assertTrue(report.contains("Ahmad Trefi"));
        assertTrue(report.contains("Java Development"));
        assertTrue(report.contains("PASSED"));
    }
}