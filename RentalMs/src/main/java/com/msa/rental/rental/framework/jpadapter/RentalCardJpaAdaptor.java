package com.msa.rental.rental.framework.jpadapter;

import com.msa.rental.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.rental.domain.model.RentalCard;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RentalCardJpaAdaptor implements RentalCardOutputPort {

  private final RentalCardRepository rentalCardRepository;

  @Override
  public Optional<RentalCard> get(String userId) {
    return rentalCardRepository.findByMemberId(userId);
  }

  @Override
  public RentalCard save(RentalCard rentalCard) {
    return rentalCardRepository.save(rentalCard);
  }
}
