package com.rootcode.library.repository;

import com.rootcode.library.entity.Book;
import com.rootcode.library.entity.BorrowBook;
import com.rootcode.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    List<BorrowBook> findAllByUserId(Long userId);

    Optional<BorrowBook> findByUserAndBookAndReturnDateIsNull(User user, Book book);
}
