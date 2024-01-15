package com.msa.rental.web;

import com.msa.rental.domain.BestBookStatisticsService;
import com.msa.rental.domain.model.BestBookStatistics;
import com.msa.rental.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics-book")
@RequiredArgsConstructor
public class BestBookStatisticsController {

  private final BestBookStatisticsService bestBookStatisticsService;

  @PostMapping()
  public ResponseEntity<BestBookStatistics> create(@RequestBody Item item) {
    return new ResponseEntity<>(bestBookStatisticsService.dealBestBook(item), HttpStatus.CREATED);
  }
}
