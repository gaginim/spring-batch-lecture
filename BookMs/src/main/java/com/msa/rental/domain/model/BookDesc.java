package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.Source;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDesc {

  private String description;
  private String author;
  private String isbn;
  private LocalDateTime publicationDate;
  private Source source;
}
