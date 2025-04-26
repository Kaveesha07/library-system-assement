package com.rootcode.library.service;

import com.rootcode.library.dto.BookRequest;
import com.rootcode.library.dto.BookResponse;
import com.rootcode.library.entity.Book;
import com.rootcode.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookResponse addBook(BookRequest bookDetails) {
        Book newBookDetails = modelMapper.map(bookDetails, Book.class);
        Book savedBook = bookRepository.save(newBookDetails);
        return modelMapper.map(savedBook, BookResponse.class);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();
    }

    @Override
    public List<BookResponse> searchBooks(String author, String year) {
        List<Book> books = bookRepository.findBooksByAuthorOrPublishedYear(author, year);
        return books.stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();

    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}
