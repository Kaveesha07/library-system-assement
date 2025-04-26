package com.rootcode.library.service;

import static com.rootcode.library.constants.MessageConstants.BOOK_UNAVAILABLE_FOR_BORROW;
import static com.rootcode.library.constants.MessageConstants.INVALID_BOOK_ID;
import static com.rootcode.library.constants.MessageConstants.INVALID_USER_ID;
import static com.rootcode.library.constants.MessageConstants.USER_NOT_FOUND;
import static com.rootcode.library.utils.MessageUtils.getMessage;
import static org.apache.logging.log4j.ThreadContext.isEmpty;

import com.rootcode.library.dto.BorrowResponse;
import com.rootcode.library.entity.Book;
import com.rootcode.library.entity.BorrowBook;
import com.rootcode.library.entity.User;
import com.rootcode.library.repository.BookRepository;
import com.rootcode.library.repository.BorrowBookRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowBookServiceImpl implements BorrowBookService {

  private final BorrowBookRepository borrowBookRepository;
  private final BookService bookService;
  private final UserService userService;
  private final ModelMapper modelMapper;
  private final BookRepository bookRepository;

  @Override
  public BorrowResponse borrowBook(Long userId, Long bookId) {
    validateUserId(userId);
    validateBookId(bookId);

    User user = userService.findUserById(userId);
    if (user == null) {
      throw new IllegalArgumentException(getMessage(USER_NOT_FOUND));
    }

    Optional<Book> bookOptional = bookService.getBookById(bookId);
    if (bookOptional.isEmpty()) {
      throw new IllegalArgumentException(getMessage(INVALID_BOOK_ID) + bookId);
    }

    Book book = bookOptional.get();
    if (book.getAvailableCopies() <= 0) {
      throw new IllegalStateException(getMessage(BOOK_UNAVAILABLE_FOR_BORROW));
    }

    BorrowBook borrowBook = new BorrowBook();
    borrowBook.setUser(user);
    borrowBook.setBook(book);
    borrowBook.setBorrowDate(LocalDate.now().atStartOfDay());

    BorrowBook savedBorrowBook = borrowBookRepository.save(borrowBook);

    return modelMapper.map(savedBorrowBook, BorrowResponse.class);
  }

  @Override
  public boolean returnBook(Long userId, Long bookId) {
    validateUserId(userId);
    validateBookId(bookId);

    User user = userService.findUserById(userId);
    if (user == null) {
      throw new IllegalArgumentException(getMessage(USER_NOT_FOUND));
    }

    Optional<Book> bookOptional = bookService.getBookById(bookId);
    if (bookOptional.isEmpty()) {
      throw new IllegalArgumentException(getMessage(INVALID_BOOK_ID));
    }

    Optional<BorrowBook> borrowBookOpt = borrowBookRepository.findByUserAndBookAndReturnDateIsNull(userId, bookId);

    if (borrowBookOpt.isEmpty()) {
      throw new IllegalStateException("No active borrow record found for this user and book.");
    }

    BorrowBook borrowBook = borrowBookOpt.get();
    borrowBook.setReturnDate(LocalDate.now().atStartOfDay());

    Book book = borrowBook.getBook();
    book.setAvailableCopies(book.getAvailableCopies() + 1);
    bookRepository.save(book);

    BorrowBook updatedBorrowBook = borrowBookRepository.save(borrowBook);

    if (updatedBorrowBook.getReturnDate() == null) {
      throw new IllegalStateException("Failed to update the return date.");
    }
    return true;
  }

  @Override
  public List<BorrowResponse> getBorrowHistory(Long userId) {
    validateUserId(userId);

    User user = userService.findUserById(userId);
    if (user == null) {
      throw new IllegalArgumentException(getMessage(USER_NOT_FOUND));
    }

    List<BorrowBook> borrowHistory = borrowBookRepository.findAllByUserId(userId);
    return borrowHistory.stream()
        .map(borrow -> modelMapper.map(borrow, BorrowResponse.class))
        .toList();
  }

  private void validateUserId(Long userId) {
    if (userId == null || userId <= 0) {
      throw new IllegalArgumentException(getMessage(INVALID_USER_ID));
    }
  }
    private void validateBookId(Long bookId) {
        if (bookId == null || bookId <= 0) {
        throw new IllegalArgumentException(getMessage(INVALID_BOOK_ID));
        }
    }
}
