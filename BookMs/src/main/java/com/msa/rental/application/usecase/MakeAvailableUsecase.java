package com.msa.rental.application.usecase;

import com.msa.rental.application.outputport.BookOutputDto;

public interface MakeAvailableUsecase {

  public BookOutputDto available(Long no);
}
