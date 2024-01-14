package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.BookOutputDto;
import com.msa.rental.application.outputport.BookOutputRepository;
import com.msa.rental.application.usecase.InquiryUsecase;
import com.msa.rental.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUsecase {

  private final BookOutputRepository bookOutputRepository;

  @Override
  public BookOutputDto getBookInfo(long no) {
    Book book = bookOutputRepository.loadBook(no);
    Assert.notNull(book, () -> "there is no data");
    return BookOutputDto.mapToDto(book);
  }
}
