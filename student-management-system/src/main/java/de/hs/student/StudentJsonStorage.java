package de.hs.student;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StudentJsonStorage {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void save(Student student, String filePath) throws IOException {
        try (Writer writer = Files.newBufferedWriter(
                Paths.get(filePath),
                StandardCharsets.UTF_8)) {
            gson.toJson(student, writer);
        }
    }

    public Student load(String filePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(
                Paths.get(filePath),
                StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Student.class);
        }
    }
}