package com.msa.rental.rental.application.inputport;

import com.msa.rental.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.rental.application.usecase.OverDueItemUsecase;
import com.msa.rental.rental.domain.model.RentalCard;
import com.msa.rental.rental.domain.model.vo.Item;
import com.msa.rental.rental.framework.web.dto.RentalCardOutputDto;
import com.msa.rental.rental.framework.web.dto.UserItemInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OverDueItemInputPort implements OverDueItemUsecase {

  private final RentalCardOutputPort rentalCardOutputPort;

  @Override
  public RentalCardOutputDto overDueItem(UserItemInputDto returnDto) {

    RentalCard rentalCard =
        rentalCardOutputPort
            .get(returnDto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("there is no card"));

    return RentalCardOutputDto.mapToDto(
        rentalCard.overdueItem(Item.of(returnDto.getItemId(), returnDto.getItemTitle())));
  }
}
