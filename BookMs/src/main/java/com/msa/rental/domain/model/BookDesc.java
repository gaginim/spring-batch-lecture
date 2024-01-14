package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.Source;
import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
public class BookDesc {

  private String description;
  private String author;
  private String isbn;
  private LocalDateTime publicationDate;
  private Source source;

  @Builder(access = AccessLevel.PRIVATE, toBuilder = true)
  public BookDesc(
      String author,
      String isbn,
      String description,
      LocalDateTime publicationDate,
      Source source) {
    this.author = author;
    this.isbn = isbn;
    this.description = description;
    this.publicationDate = publicationDate;
    this.source = source;
  }

  public static BookDesc createBookDesc(
      String author,
      String isbn,
      String description,
      LocalDateTime publicationDate,
      Source source) {
    return BookDesc.builder()
        .author(author)
        .isbn(isbn)
        .description(description)
        .publicationDate(publicationDate)
        .source(source)
        .build();
  }
}
