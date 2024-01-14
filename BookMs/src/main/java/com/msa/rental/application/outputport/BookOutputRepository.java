package com.msa.rental.application.outputport;

import com.msa.rental.domain.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOutputRepository {

  public Book loadBook(long no);

  public Book save(Book book);
}
