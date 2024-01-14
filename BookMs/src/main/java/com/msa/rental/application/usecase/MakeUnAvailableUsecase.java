package com.msa.rental.application.usecase;

import com.msa.rental.application.outputport.BookOutputDto;

public interface MakeUnAvailableUsecase {

    public BookOutputDto unAvailable(Long no);
}
