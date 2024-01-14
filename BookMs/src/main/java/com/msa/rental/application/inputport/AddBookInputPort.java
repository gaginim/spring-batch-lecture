package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.BookOutputDto;
import com.msa.rental.application.usecase.AddBookUsecase;
import com.msa.rental.domain.model.Book;
import com.msa.rental.framework.jpadaptor.BookOutputRepository;
import com.msa.rental.framework.web.dto.BookInfoDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddBookInputPort implements AddBookUsecase {

  private final BookOutputRepository bookOutputRepository;

  @Transactional
  @Override
  public BookOutputDto addBook(BookInfoDto bookInfoDto) {
    Book book =
        Book.createBook(
            bookInfoDto.getTitle(),
            bookInfoDto.getAuthor(),
            bookInfoDto.getIsbn(),
            bookInfoDto.getDescription(),
            bookInfoDto.getPublicationDate(),
            bookInfoDto.getSource(),
            bookInfoDto.getClassification(),
            bookInfoDto.getLocation());

    return BookOutputDto.mapToDto(bookOutputRepository.save(book));
  }
}
