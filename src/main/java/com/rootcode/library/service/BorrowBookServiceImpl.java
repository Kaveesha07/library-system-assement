package com.rootcode.library.service;

import com.rootcode.library.dto.BorrowResponse;
import com.rootcode.library.entity.Book;
import com.rootcode.library.entity.BorrowBook;
import com.rootcode.library.entity.User;
import com.rootcode.library.repository.BorrowBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowBookServiceImpl implements BorrowBookService {

    private final BorrowBookRepository borrowBookRepository;
    private final BookService bookService;
    private final UserService userService;

  @Override
  public BorrowResponse borrowBook(Long userId, Long bookId) {

      if (userId == null || userId <= 0) {
          throw new IllegalArgumentException("User ID must be a positive number.");
      }

      if (bookId == null || bookId <= 0) {
          throw new IllegalArgumentException("Book ID must be a positive number.");
      }

      User user = userService.findUserById(userId);
      if (user == null) {
          throw new IllegalArgumentException("Invalid user ID: " + userId);
      }

      Optional<Book> bookOptional = bookService.getBookById(bookId);
      if (bookOptional.isEmpty()) {
          throw new IllegalArgumentException("Invalid book ID: " + bookId);
      }

      Book book = bookOptional.get();
      if (book.getAvailableCopies() <= 0) {
          throw new IllegalStateException("Book is currently not available for borrowing.");
      }

      BorrowBook borrowBook = new BorrowBook();
      borrowBook.setUser(user);
      borrowBook.setBook(book);
      borrowBook.setBorrowDate(LocalDate.now().atStartOfDay());

      BorrowBook savedBorrowBook = borrowBookRepository.save(borrowBook);

      return new BorrowResponse();


  }

  @Override
  public void returnBook(Long userId, Long bookId) {}

    @Override
    public List<BorrowResponse> getBorrowHistory(Long userId) {
        return null;
    }
}
