package com.msa.rental.domain.repository;

import com.msa.rental.domain.model.BestBookStatistics;
import com.msa.rental.domain.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestBookStatisticsRepository extends MongoRepository<BestBookStatistics, String> {

  public BestBookStatistics findBestBookStatisticsByItem(Item item);
}
