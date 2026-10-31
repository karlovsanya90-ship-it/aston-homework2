package com.aston.homework.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBooksAreEqualAndHaveSameHashCode() {
        Book book1 = new Book("Гарри Поттер", "Роулинг", 300, 1997);
        Book book2 = new Book("Гарри Поттер", "Роулинг", 300, 1997);

        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void testBooksAreNotEqual() {
        Book book1 = new Book("Гарри Поттер", "Роулинг", 300, 1997);
        Book book2 = new Book("Властелин колец", "Толкин", 1000, 1954);

        assertNotEquals(book1, book2);
    }
}