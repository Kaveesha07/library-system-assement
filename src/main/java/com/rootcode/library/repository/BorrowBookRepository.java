package com.rootcode.library.repository;

import com.rootcode.library.entity.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook,Long> {

}
