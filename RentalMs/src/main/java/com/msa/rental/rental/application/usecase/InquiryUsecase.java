package com.msa.rental.rental.application.usecase;

import com.msa.rental.rental.framework.web.dto.RentITemOutputDto;
import com.msa.rental.rental.framework.web.dto.RentalCardOutputDto;
import com.msa.rental.rental.framework.web.dto.ReturnItemOutputDto;
import com.msa.rental.rental.framework.web.dto.UserInputDto;
import java.util.List;
import java.util.Optional;

public interface InquiryUsecase {

  Optional<RentalCardOutputDto> getRentalCard(UserInputDto dto);

  Optional<List<RentITemOutputDto>> getAllRentItem(UserInputDto dto);

  Optional<List<ReturnItemOutputDto>> getAllReturnItem(UserInputDto dto);
}
