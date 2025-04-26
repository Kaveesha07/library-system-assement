package com.rootcode.library.service;

import com.rootcode.library.dto.BorrowResponse;

import java.util.List;

public interface BorrowBookService {

    BorrowResponse borrowBook(Long userId, Long bookId);

    void returnBook(Long userId, Long bookId);

    List<BorrowResponse> getBorrowHistory(Long userId);

}
