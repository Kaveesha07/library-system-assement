package com.rootcode.library.repository;

import com.rootcode.library.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')) " +
            "OR str(b.publishedYear) LIKE CONCAT('%', :year, '%')")
    List<Book> findBooksByAuthorOrPublishedYear(@Param("author") String author, @Param("year") String year);
}
