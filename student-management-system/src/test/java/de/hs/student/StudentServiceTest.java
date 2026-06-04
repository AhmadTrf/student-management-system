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
}