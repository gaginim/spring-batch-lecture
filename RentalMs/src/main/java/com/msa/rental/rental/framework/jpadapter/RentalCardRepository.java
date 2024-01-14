package com.msa.rental.rental.framework.jpadapter;

import com.msa.rental.rental.domain.model.RentalCard;
import com.msa.rental.rental.domain.model.vo.RentalCardNo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalCardRepository extends JpaRepository<RentalCard, RentalCardNo> {

  @Query("select m from RentalCard m where m.member.id = :id")
  Optional<RentalCard> findByMemberId(@Param("id") String id);

  @Query("select m from RentalCard m where m.rentalCardNo.no = :id")
  Optional<RentalCard> findById(@Param("id") Long id);
}
