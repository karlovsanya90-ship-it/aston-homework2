package com.aston.homework;

import com.aston.homework.io.StudentFileReader;
import com.aston.homework.model.Book;
import com.aston.homework.model.Student;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("=== ВЫПОЛНЕНИЕ ДОМАШНЕГО ЗАДАНИЯ ===");
        Path path = Paths.get("src/main/resources/students.txt");
        List<Student> students = StudentFileReader.readStudents(path);

        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .distinct()
                .sorted(Comparator.comparingInt(Book::getPages).thenComparing(Book::getTitle))
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        () -> System.out.println("Книга отсутствует")
                );

        System.out.println("\n=== БОНУС: ПРОВЕРКА ВСЕХ ФАЙЛОВ В ПАПКЕ ===");
        Path resourcesDir = Paths.get("src/main/resources");

        try (Stream<Path> paths = Files.list(resourcesDir)) {
            paths.filter(p -> p.toString().endsWith(".txt"))
                    .forEach(p -> {
                        String fileName = p.getFileName().toString();
                        System.out.print("Файл [" + fileName + "]: ");
                        try {
                            List<Student> s = StudentFileReader.readStudents(p);
                            System.out.println("✅ ОК (Студентов: " + s.size() + ")");
                        } catch (Exception e) {
                            System.out.println("❌ ОШИБКА: " + e.getMessage());
                        }
                    });
        }
    }
}