package com.rootcode.library.controller;

import com.rootcode.library.dto.BookRequest;
import com.rootcode.library.dto.BookResponse;
import com.rootcode.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookDetails) {
        BookResponse bookResponse = bookService.addBook(bookDetails);
        return new ResponseEntity<>(bookResponse, CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> searchBooks(@RequestParam(required = false) String author, @RequestParam(required = false) String year) {
        List<BookResponse> books = bookService.searchBooks(author, year);
        return new ResponseEntity<>(books, OK);
    }
}
