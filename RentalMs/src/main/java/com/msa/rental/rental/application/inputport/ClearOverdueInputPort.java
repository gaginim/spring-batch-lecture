package com.msa.rental.rental.application.inputport;

import com.msa.rental.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.rental.application.usecase.ClearOverdueItemUsecase;
import com.msa.rental.rental.domain.model.RentalCard;
import com.msa.rental.rental.framework.web.dto.ClearOverDueInfoDto;
import com.msa.rental.rental.framework.web.dto.RentalResultOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClearOverdueInputPort implements ClearOverdueItemUsecase {

  private final RentalCardOutputPort rentalCardOutputPort;

  @Override
  public RentalResultOutputDto clearOverdue(ClearOverDueInfoDto clearOverDueInfoDto) {
    RentalCard rentalCard =
        rentalCardOutputPort
            .get(clearOverDueInfoDto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("there is no card"));

    rentalCard.changeRentalAvailable(clearOverDueInfoDto.getPoints());

    return RentalResultOutputDto.mapToDto(rentalCard);
  }
}
