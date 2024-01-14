package com.msa.rental.rental.application.usecase;

import com.msa.rental.rental.framework.web.dto.RentalCardOutputDto;
import com.msa.rental.rental.framework.web.dto.UserInputDto;

public interface CreateRentalCardUsecase {

  public RentalCardOutputDto createRentalCard(UserInputDto userInputDto);

}
