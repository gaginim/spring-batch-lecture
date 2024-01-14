package com.msa.rental.rental.application.inputport;

import com.msa.rental.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.rental.application.usecase.ReturnItemUsecase;
import com.msa.rental.rental.domain.model.RentalCard;
import com.msa.rental.rental.domain.model.vo.Item;
import com.msa.rental.rental.framework.web.dto.RentalCardOutputDto;
import com.msa.rental.rental.framework.web.dto.UserItemInputDto;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReturnItemInputPort implements ReturnItemUsecase {

  private final RentalCardOutputPort rentalCardOutputPort;

  @Transactional
  @Override
  public RentalCardOutputDto returnItem(UserItemInputDto returnDto) {
    RentalCard rentalCard =
        rentalCardOutputPort
            .get(returnDto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("there is no card info"));

    Item returnItem = Item.of(returnDto.getItemId(), returnDto.getItemTitle());

    rentalCard.returnItem(returnItem, LocalDateTime.now());
    return RentalCardOutputDto.mapToDto(rentalCard);
  }
}
