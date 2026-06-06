package de.hs.student;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Handles JSON persistence for students.
 */
public class StudentJsonStorage {

  private final Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();

  /**
   * Saves a student as JSON.
   *
   * @param student student to save
   * @param filePath destination file
   * @throws IOException if writing fails
   */
  public void save(Student student, String filePath) throws IOException {
    try (Writer writer = Files.newBufferedWriter(
        Paths.get(filePath),
        StandardCharsets.UTF_8)) {
      gson.toJson(student, writer);
    }
  }

  /**
   * Loads a student from JSON.
   *
   * @param filePath source file
   * @return loaded student
   * @throws IOException if reading fails
   */
  public Student load(String filePath) throws IOException {
    try (Reader reader = Files.newBufferedReader(
        Paths.get(filePath),
        StandardCharsets.UTF_8)) {
      return gson.fromJson(reader, Student.class);
    }
  }
}