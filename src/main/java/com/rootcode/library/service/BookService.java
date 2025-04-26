package com.rootcode.library.service;

import com.rootcode.library.dto.BookRequest;
import com.rootcode.library.dto.BookResponse;
import java.util.List;

public interface BookService {

    BookResponse addBook(BookRequest bookDetails);
    List<BookResponse> getAllBooks();
    List<BookResponse> searchBooks(String author, int year);

}
