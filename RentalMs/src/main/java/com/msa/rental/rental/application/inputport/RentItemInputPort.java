package com.msa.rental.rental.application.inputport;

import com.msa.rental.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.rental.application.usecase.RentItemUsecase;
import com.msa.rental.rental.domain.model.RentalCard;
import com.msa.rental.rental.domain.model.vo.IDName;
import com.msa.rental.rental.domain.model.vo.Item;
import com.msa.rental.rental.framework.web.dto.RentalCardOutputDto;
import com.msa.rental.rental.framework.web.dto.UserItemInputDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentItemInputPort implements RentItemUsecase {

  private final RentalCardOutputPort rentalCardOutputPort;

  @Transactional
  @Override
  public RentalCardOutputDto rentItem(UserItemInputDto rentDto) {

    RentalCard rentalCard =
        rentalCardOutputPort
            .get(rentDto.getUserId())
            .orElseGet(() -> RentalCard.of(IDName.of(rentDto.getUserId(), rentDto.getUserName())));

    rentalCard.rentalItem(Item.of(rentDto.getItemId(), rentDto.getItemTitle()));

    // 영속성 컨텍스트 때문에 save 안해도 자동으로 저장될거라 이건 주석처리 해도됨
    // RentalCard result = rentalCardOutputPort.save(rentalCard);

    return RentalCardOutputDto.mapToDto(rentalCard);
  }
}
