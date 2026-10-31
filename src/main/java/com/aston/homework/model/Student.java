package com.aston.homework.model;

import java.util.List;

public class Student {
    private final String name;
    private final List<Book> books;

    public Student (String name, List<Book> books){
        this.name = name;
        this.books = List.copyOf(books);
    }

    public String getName() {
        return name;
    }
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString(){
        return "Student{name='" + name + "', books=" + books + '}';
    }
}
