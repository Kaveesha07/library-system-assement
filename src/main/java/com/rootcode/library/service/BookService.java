package com.rootcode.library.service;

import com.rootcode.library.dto.BookRequest;
import com.rootcode.library.dto.BookResponse;
import com.rootcode.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookResponse addBook(BookRequest bookDetails);
    List<BookResponse> getAllBooks();
    List<BookResponse> searchBooks(String author, String year);

    Optional<Book> getBookById(Long id);

}
