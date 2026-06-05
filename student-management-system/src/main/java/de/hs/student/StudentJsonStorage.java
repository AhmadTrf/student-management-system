package de.hs.student;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StudentJsonStorage {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void save(Student student, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(student, writer);
        }
    }

    public Student load(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Student.class);
        }
    }
}