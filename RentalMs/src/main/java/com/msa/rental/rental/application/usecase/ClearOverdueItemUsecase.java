package com.msa.rental.rental.application.usecase;

import com.msa.rental.rental.framework.web.dto.ClearOverDueInfoDto;
import com.msa.rental.rental.framework.web.dto.RentalResultOutputDto;

public interface ClearOverdueItemUsecase {

  RentalResultOutputDto clearOverdue(ClearOverDueInfoDto clearOverDueInfoDto);
}
