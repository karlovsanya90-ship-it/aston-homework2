package com.aston.homework.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testBooksListIsImmutable() {
        Book book = new Book("Книга", "Автор", 100, 2000);
        List<Book> mutableList = new ArrayList<>();
        mutableList.add(book);

        Student student = new Student("Иван", mutableList);

        assertThrows(UnsupportedOperationException.class, () -> {
            student.getBooks().add(new Book("Вторая книга", "Автор", 200, 2001));
        });
    }
}