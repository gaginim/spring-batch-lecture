package com.msa.rental.framework.jpadaptor;

import com.msa.rental.domain.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOutputRepository extends JpaRepository<Book, Long> {

  Optional<Book> findById(long no);

  Book save(Book book);
}
