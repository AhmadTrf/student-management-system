package de.hs.student;

import junit.framework.TestCase;

public class StudentServiceTest extends TestCase {

    private StudentService service;

    @Override
    protected void setUp() {
        StudentRepository repository = new StudentRepository();
        service = new StudentService(repository);
    }

    public void testAddStudent() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        service.addStudent(student);

        assertEquals(1, service.getAllStudents().size());
    }

    public void testFindStudentById() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        service.addStudent(student);

        assertTrue(service.getStudentById("1").isPresent());
        assertEquals("Ali", service.getStudentById("1").get().getFirstName());
    }

    public void testRemoveStudent() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        service.addStudent(student);

        boolean removed = service.removeStudent("1");

        assertTrue(removed);
        assertEquals(0, service.getAllStudents().size());
    }

    public void testRemoveUnknownStudent() {
        boolean removed = service.removeStudent("999");

        assertFalse(removed);
    }

        public void testFindStudentByEmail() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        service.addStudent(student);

        assertTrue(service.getStudentByEmail("ali@example.com").isPresent());
        assertEquals("1", service.getStudentByEmail("ali@example.com").get().getId());
    }

    public void testAddGradeToStudent() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        service.addStudent(student);
        service.addGradeToStudent("1", new Grade("Java Development", 1.7));

        assertEquals(1, service.getStudentById("1").get().getGrades().size());
    }

    public void testCalculateAverageGradeForStudent() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        service.addStudent(student);
        service.addGradeToStudent("1", new Grade("Java Development", 1.7));
        service.addGradeToStudent("1", new Grade("Software Engineering", 2.3));

        assertEquals(2.0, service.calculateAverageGrade("1"));
    }

    public void testAddGradeToUnknownStudentFails() {
        try {
            service.addGradeToStudent("999", new Grade("Java Development", 1.7));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals("Student not found", exception.getMessage());
        }
    }
}