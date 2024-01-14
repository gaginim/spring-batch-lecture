package com.msa.rental.rental.application.inputport;

import com.msa.rental.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.rental.application.usecase.CreateRentalCardUsecase;
import com.msa.rental.rental.domain.model.RentalCard;
import com.msa.rental.rental.domain.model.vo.IDName;
import com.msa.rental.rental.framework.web.dto.RentalCardOutputDto;
import com.msa.rental.rental.framework.web.dto.UserInputDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRentalCardInputPort implements CreateRentalCardUsecase {

  private final RentalCardOutputPort rentalCardOutputPort;

  @Transactional
  @Override
  public RentalCardOutputDto createRentalCard(UserInputDto owner) {

    // RentalCard.of 를 사용해서 서비스 계층이 아니라 도메인 계층에 위임해서 비즈니스 처리를 하는걸 "흐름제어" 라고 한다.
    return RentalCardOutputDto.mapToDto(
        rentalCardOutputPort.save(
            RentalCard.of(IDName.of(owner.getUserId(), owner.getUserName()))));
  }
}
