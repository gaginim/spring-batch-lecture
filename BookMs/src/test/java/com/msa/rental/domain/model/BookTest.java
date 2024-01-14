package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.Classification;
import com.msa.rental.domain.model.vo.Location;
import com.msa.rental.domain.model.vo.Source;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookTest {

  @Test
  void createBookDesc() {
    BookDesc newBook =
        BookDesc.createBookDesc("han", "1234", "test boook", LocalDateTime.now(), Source.SUPPLY);

    Assertions.assertNotNull(newBook);
  }

  @Test
  void createBook() {
    Book book =
        Book.createBook(
            "my diary",
            "han",
            "1234",
            "test book",
            LocalDateTime.now(),
            Source.DONATION,
            Classification.LITERATURE,
            Location.KO);

    Assertions.assertNotNull(book);
  }
}
