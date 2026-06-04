package de.hs.student;

import junit.framework.TestCase;

public class StudentTest extends TestCase {

    public void testGetFullName() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        assertEquals("Ali Muster", student.getFullName());
    }

    public void testAddGrade() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");
        Grade grade = new Grade("Java Development", 1.7);

        student.addGrade(grade);

        assertEquals(1, student.getGrades().size());
    }

    public void testCalculateAverageGrade() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        student.addGrade(new Grade("Java Development", 1.7));
        student.addGrade(new Grade("Software Engineering", 2.3));

        assertEquals(2.0, student.calculateAverageGrade());
    }

    public void testCannotCalculateAverageWithoutGrades() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        try {
            student.calculateAverageGrade();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException exception) {
            assertEquals("Cannot calculate average without grades", exception.getMessage());
        }
    }

    public void testPassedAllCourses() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        student.addGrade(new Grade("Java Development", 1.7));
        student.addGrade(new Grade("Software Engineering", 4.0));

        assertTrue(student.hasPassedAllCourses());
    }

    public void testNotPassedAllCourses() {
        Student student = new Student("1", "Ali", "Muster", "ali@example.com");

        student.addGrade(new Grade("Java Development", 1.7));
        student.addGrade(new Grade("Software Engineering", 5.0));

        assertFalse(student.hasPassedAllCourses());
    }
}