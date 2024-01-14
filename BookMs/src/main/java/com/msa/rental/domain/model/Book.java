package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.BookStatus;
import com.msa.rental.domain.model.vo.Classification;
import com.msa.rental.domain.model.vo.Location;
import com.msa.rental.domain.model.vo.Source;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long no;

  private String title;

  @Embedded private BookDesc desc;
  private Classification classification;
  private BookStatus bookStatus;
  private Location location;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  public Book(
      String title,
      BookDesc desc,
      Classification classification,
      BookStatus bookStatus,
      Location location) {
    this.title = title;
    this.desc = desc;
    this.classification = classification;
    this.bookStatus = bookStatus;
    this.location = location;
  }

  public static Book createBook(
      String title,
      String author,
      String isbn,
      String description,
      LocalDateTime publishDt,
      Source source,
      Classification classification,
      Location location) {

    return Book.builder()
        .title(title)
        .desc(BookDesc.createBookDesc(author, isbn, description, publishDt, source))
        .classification(classification)
        .location(location)
        .bookStatus(BookStatus.ENTERED)
        .build();
  }

  public Book makeAvailable() {
    this.bookStatus = BookStatus.AVAILABLE;
    return this;
  }

  public Book makeUnAvailable() {
    this.bookStatus = BookStatus.UNAVAILABLE;
    return this;
  }
}
