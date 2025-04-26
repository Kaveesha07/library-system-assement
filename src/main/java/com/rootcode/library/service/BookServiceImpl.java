package com.rootcode.library.service;

import com.rootcode.library.dto.BookRequest;
import com.rootcode.library.dto.BookResponse;
import com.rootcode.library.entity.Book;
import com.rootcode.library.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;


    /**
     * This method is used to add a new book to the library.
     * It takes a BookRequest object as input, which contains the details of the book to be added.
     * The method maps the BookRequest object to a Book entity, saves it.
     * and returns a BookResponse object containing the details of the saved book.
     **/
    @Override
    public BookResponse addBook(BookRequest bookDetails) {
        Book newBookDetails = modelMapper.map(bookDetails, Book.class);
        Book savedBook = bookRepository.save(newBookDetails);
        return modelMapper.map(savedBook, BookResponse.class);
    }


    /**
     * This method is used to get all books available.
     **/
    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();
    }

    /**
     * This method is used to search for books in the library based on the author or published year.
     * It takes an author name and a published year as input parameters.
     * The method retrieves a list of books that match the search criteria and maps them to BookResponse objects.
     * @param author
     * @param year
     */
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
