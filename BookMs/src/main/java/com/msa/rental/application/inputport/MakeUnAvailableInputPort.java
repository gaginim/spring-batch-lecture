package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.BookOutputDto;
import com.msa.rental.application.outputport.BookOutputRepository;
import com.msa.rental.application.usecase.MakeUnAvailableUsecase;
import com.msa.rental.domain.model.Book;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class MakeUnAvailableInputPort implements MakeUnAvailableUsecase {

  private final BookOutputRepository bookOutputRepository;

  @Transactional
  @Override
  public BookOutputDto unAvailable(Long no) {
    Book book = bookOutputRepository.loadBook(no);
    Assert.notNull(book, () -> "there is no data");
    return BookOutputDto.mapToDto(book.makeUnAvailable());
  }
}
