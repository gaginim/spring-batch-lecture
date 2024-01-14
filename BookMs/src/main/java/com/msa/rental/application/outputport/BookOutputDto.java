package com.msa.rental.application.outputport;

import com.msa.rental.domain.model.Book;
import lombok.*;

@NoArgsConstructor
@Getter
public class BookOutputDto {

  private long bookNo;
  private String title;
  private String status;

  @Builder(access = AccessLevel.PRIVATE)
  public BookOutputDto(long bookNo, String title, String status) {
    this.bookNo = bookNo;
    this.title = title;
    this.status = status;
  }

  public static BookOutputDto mapToDto(final Book book) {
    return BookOutputDto.builder()
        .bookNo(book.getNo())
        .title(book.getTitle())
        .status(book.getBookStatus().name())
        .build();
  }
}
