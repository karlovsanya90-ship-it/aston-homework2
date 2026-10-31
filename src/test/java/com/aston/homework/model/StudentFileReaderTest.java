package com.aston.homework.model;

import com.aston.homework.io.StudentFileReader;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class StudentFileReaderTest {

    @Test
    void testReadGoodFile() {
        Path path = Paths.get("src/main/resources/students_standard.txt");
        // Проверяем, что нормальный файл читается без ошибок
        assertDoesNotThrow(() -> StudentFileReader.readStudents(path));
    }

    @Test
    void testReadBrokenFile() {
        Path path = Paths.get("src/main/resources/students_broken_data.txt");
        // Проверяем, что на битом файле программа выбрасывает ошибку (Exception)
        assertThrows(RuntimeException.class, () -> {
            StudentFileReader.readStudents(path);
        });
    }
}