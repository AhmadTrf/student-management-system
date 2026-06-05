package de.hs.student;

import java.io.File;
import junit.framework.TestCase;

public class StudentJsonStorageTest extends TestCase {

    public void testSaveAndLoadStudent() throws Exception {
        Student student = new Student(
                "1",
                "Ahmad",
                "Trefi",
                "trefiahm@hs-albsig.de"
        );

        student.addGrade(new Grade("Java Development", 1.7));

        File tempFile = File.createTempFile("student", ".json");

        StudentJsonStorage storage = new StudentJsonStorage();

        storage.save(student, tempFile.getAbsolutePath());

        Student loadedStudent = storage.load(tempFile.getAbsolutePath());

        assertEquals(student.getId(), loadedStudent.getId());
        assertEquals(student.getFullName(), loadedStudent.getFullName());
        assertEquals(1, loadedStudent.getGrades().size());

        tempFile.delete();
    }
}