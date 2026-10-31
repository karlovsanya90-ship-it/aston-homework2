package com.aston.homework.io;

import com.aston.homework.model.Book;
import com.aston.homework.model.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class StudentFileReader {

    public static List<Student> readStudents(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .map(line -> parseStudent(line))
                    .toList();
        }
    }

    private static Student parseStudent(String line) {
        String[] parts = line.split(";");
        if (parts.length < 2) {
            throw new RuntimeException("Строка не содержит книг: " + line);
        }

        String name = parts[0];
        List<Book> books = Stream.of(parts)
                .skip(1)
                .map(part -> {
                    try {
                        int yearComma = part.lastIndexOf(",");
                        int pagesComma = part.lastIndexOf(",", yearComma - 1);
                        int authorComma = part.lastIndexOf(",", pagesComma - 1);

                        String title = part.substring(0, authorComma);
                        String author = part.substring(authorComma + 1, pagesComma);
                        int pages = Integer.parseInt(part.substring(pagesComma + 1, yearComma).trim());
                        int year = Integer.parseInt(part.substring(yearComma + 1).trim());

                        return new Book(title, author, pages, year);
                    } catch (Exception e) {
                        throw new RuntimeException("Неверный формат книги: " + part);
                    }
                })
                .toList();

        return new Student(name, books);
    }
}