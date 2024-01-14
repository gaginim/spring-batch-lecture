package com.msa.rental.application.usecase;

import com.msa.rental.application.outputport.BookOutputDto;

public interface InquiryUsecase {

    public BookOutputDto getBookInfo(long no);

}
