package com.msa.rental.application.usecase;

import com.msa.rental.application.outputport.BookOutputDto;
import com.msa.rental.framework.web.dto.BookInfoDto;

public interface AddBookUsecase {

  public BookOutputDto addBook(BookInfoDto bookInfoDto);
}
