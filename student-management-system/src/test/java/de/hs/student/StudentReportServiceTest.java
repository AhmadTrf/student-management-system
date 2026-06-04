package de.hs.student;

import junit.framework.TestCase;

public class StudentReportServiceTest extends TestCase {

    public void testGenerateReport() {

        StudentRepository repository = new StudentRepository();

        Student student =
                new Student("1",
                        "Ali",
                        "Muster",
                        "ali@example.com");

        student.addGrade(new Grade("Java", 1.7));
        student.addGrade(new Grade("Software Engineering", 2.3));

        repository.save(student);

        StudentReportService reportService =
                new StudentReportService(repository);

        String report =
                reportService.generateReport("1");

        assertTrue(report.contains("Ali Muster"));
        assertTrue(report.contains("Java"));
        assertTrue(report.contains("Average Grade"));
        assertTrue(report.contains("PASSED"));
    }

    public void testUnknownStudent() {

        StudentRepository repository =
                new StudentRepository();

        StudentReportService reportService =
                new StudentReportService(repository);

        try {
            reportService.generateReport("999");

            fail("Expected IllegalArgumentException");

        } catch (IllegalArgumentException exception) {

            assertEquals(
                    "Student not found",
                    exception.getMessage()
            );
        }
    }
}