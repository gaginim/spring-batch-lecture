package com.msa.rental.rental.application.outputport;

import com.msa.rental.rental.domain.model.RentalCard;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalCardOutputPort {
  Optional<RentalCard> get(String userId);

  RentalCard save(RentalCard rentalCard);
}
