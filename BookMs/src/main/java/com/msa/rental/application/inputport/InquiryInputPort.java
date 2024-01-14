package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.BookOutputDto;
import com.msa.rental.application.usecase.InquiryUsecase;
import com.msa.rental.domain.model.Book;
import com.msa.rental.framework.jpadaptor.BookOutputRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUsecase {

  private final BookOutputRepository bookOutputRepository;

  @Override
  public BookOutputDto getBookInfo(long no) {
    Book book =
        bookOutputRepository
            .findById(no)
            .orElseThrow(() -> new IllegalArgumentException("there is no data"));
    return BookOutputDto.mapToDto(book);
  }
}
