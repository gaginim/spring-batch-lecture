package com.msa.rental.rental.application.usecase;

import com.msa.rental.rental.framework.web.dto.RentalCardOutputDto;
import com.msa.rental.rental.framework.web.dto.UserItemInputDto;

public interface OverDueItemUsecase {
  public RentalCardOutputDto overDueItem(UserItemInputDto returnDto);
}
