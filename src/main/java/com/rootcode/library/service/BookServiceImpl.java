package com.rootcode.library.service;

import com.rootcode.library.dto.BookRequest;
import com.rootcode.library.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{


    @Override
    public BookResponse addBook(BookRequest bookDetails) {
        return null;
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return null;
    }

    @Override
    public List<BookResponse> searchBooks(String author, int year) {
        return null;
    }
}
