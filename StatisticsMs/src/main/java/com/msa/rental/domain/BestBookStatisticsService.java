package com.msa.rental.domain;

import com.msa.rental.domain.model.BestBookStatistics;
import com.msa.rental.domain.model.Item;
import com.msa.rental.domain.repository.BestBookStatisticsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class BestBookStatisticsService {

  private final BestBookStatisticsRepository bestBookStatisticsRepository;

  public List<BestBookStatistics> getAllBooks() {
    return bestBookStatisticsRepository.findAll();
  }

  public BestBookStatistics getBookById(String id) {
    return bestBookStatisticsRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("there is no books"));
  }

  public BestBookStatistics dealBestBook(final Item item) {
    BestBookStatistics bestBookStatistics =
        bestBookStatisticsRepository.findBestBookStatisticsByItem(item);

    if (!ObjectUtils.isEmpty(bestBookStatistics)) bestBookStatistics.increase();
    else bestBookStatistics = BestBookStatistics.register(item);
    return bestBookStatisticsRepository.save(bestBookStatistics);
  }

  public BestBookStatistics updateItemDetail(String id, BestBookStatistics bookStatistics) {
    BestBookStatistics bestBookStatistics =
        bestBookStatisticsRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("cannot find bestbook info."));

    return bestBookStatisticsRepository.save(
        bestBookStatistics.update(bookStatistics.getItem(), bookStatistics.getRentCount()));
  }
}
