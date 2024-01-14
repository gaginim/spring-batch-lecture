package com.msa.rental.framework.web;

import com.msa.rental.application.outputport.BookOutputDto;
import com.msa.rental.application.usecase.AddBookUsecase;
import com.msa.rental.application.usecase.InquiryUsecase;
import com.msa.rental.application.usecase.MakeAvailableUsecase;
import com.msa.rental.application.usecase.MakeUnAvailableUsecase;
import com.msa.rental.framework.web.dto.BookInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

  private final AddBookUsecase addBookUsecase;
  private final InquiryUsecase inquiryUsecase;
  private final MakeAvailableUsecase makeAvailableUsecase;
  private final MakeUnAvailableUsecase makeUnAvailableUsecase;

  @PostMapping()
  public ResponseEntity<BookOutputDto> create(@RequestBody BookInfoDto info) {
    return new ResponseEntity<>(addBookUsecase.addBook(info), HttpStatus.CREATED);
  }

  @GetMapping("/{no}")
  public ResponseEntity<BookOutputDto> getInfo(@PathVariable("no") Long no) {
    return new ResponseEntity<>(inquiryUsecase.getBookInfo(no), HttpStatus.OK);
  }
}
