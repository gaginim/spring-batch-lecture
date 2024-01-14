package com.msa.rental.rental.application.inputport;

import com.msa.rental.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.rental.application.usecase.InquiryUsecase;
import com.msa.rental.rental.framework.web.dto.*;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUsecase {

  private final RentalCardOutputPort rentalCardOutputPort;

  @Override
  public Optional<RentalCardOutputDto> getRentalCard(UserInputDto dto) {
    return rentalCardOutputPort.get(dto.getUserId()).map(RentalCardOutputDto::mapToDto);
  }

  @Override
  public Optional<List<RentITemOutputDto>> getAllRentItem(UserInputDto dto) {
    return rentalCardOutputPort
        .get(dto.getUserId())
        .map(
            loadCard ->
                loadCard.getRentalItems().stream().map(RentITemOutputDto::mapToDto).toList());
  }

  @Override
  public Optional<List<ReturnItemOutputDto>> getAllReturnItem(UserInputDto dto) {
    return rentalCardOutputPort
        .get(dto.getUserId())
        .map(
            loadCard ->
                loadCard.getReturnItems().stream().map(ReturnItemOutputDto::mapToDto).toList());
  }
}
