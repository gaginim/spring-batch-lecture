package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.BookOutputDto;
import com.msa.rental.application.usecase.MakeUnAvailableUsecase;
import com.msa.rental.domain.model.Book;
import com.msa.rental.framework.jpadaptor.BookOutputRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeUnAvailableInputPort implements MakeUnAvailableUsecase {

  private final BookOutputRepository bookOutputRepository;

  @Transactional
  @Override
  public BookOutputDto unAvailable(Long no) {
    Book book =
        bookOutputRepository
            .findById(no)
            .orElseThrow(() -> new IllegalArgumentException("there is no data"));
    return BookOutputDto.mapToDto(book.makeUnAvailable());
  }
}
