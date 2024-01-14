package com.msa.rental.rental.framework.web;

import com.msa.rental.rental.application.usecase.*;
import com.msa.rental.rental.framework.web.dto.*;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentalcard")
@RequiredArgsConstructor
public class RentalController {

  private final RentItemUsecase rentItemUsecase;
  private final ReturnItemUsecase returnItemUsecase;
  private final CreateRentalCardUsecase createRentalCardUsecase;
  private final OverDueItemUsecase overDueItemUsecase;
  private final ClearOverdueItemUsecase clearOverdueItemUsecase;
  private final InquiryUsecase inquiryUsecase;

  @ApiOperation(value = "도서카드 생성", notes = "사용자정보 -> 도서카드정보")
  @PostMapping()
  public ResponseEntity<RentalCardOutputDto> createRentalCard(
      @RequestBody UserInputDto userInputDto) {

    RentalCardOutputDto rentalCardOutputDto =
        createRentalCardUsecase.createRentalCard(userInputDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(rentalCardOutputDto);
  }

  @ApiOperation(value = "도서카드 조회", notes = "사용자정보(id) -> 도서카드정보")
  @GetMapping("/{userId}")
  public ResponseEntity<RentalCardOutputDto> getRentalCard(@PathVariable String userId) {

    Optional<RentalCardOutputDto> rentalCardOutputDto =
        inquiryUsecase.getRentalCard(new UserInputDto(userId, ""));

    return ResponseEntity.status(HttpStatus.CREATED).body(rentalCardOutputDto.get());
  }

  @ApiOperation(value = "여도서목록 조회", notes = "사용자정보(id) -> 대여도서목록 조회")
  @GetMapping("/{userId}/rent")
  public ResponseEntity<List<RentITemOutputDto>> getAllRentItem(@PathVariable String userId) {

    Optional<List<RentITemOutputDto>> rentalCardOutputDto =
        inquiryUsecase.getAllRentItem(new UserInputDto(userId, ""));

    return ResponseEntity.status(HttpStatus.CREATED).body(rentalCardOutputDto.get());
  }

  @ApiOperation(value = "반납도서목록 조회", notes = "사용자정보(id) -> 반납도서목록 조회")
  @GetMapping("/{userId}/return")
  public ResponseEntity<List<ReturnItemOutputDto>> getAllReturnItem(@PathVariable String userId) {

    Optional<List<ReturnItemOutputDto>> rentalCardOutputDto =
        inquiryUsecase.getAllReturnItem(new UserInputDto(userId, ""));

    return ResponseEntity.status(HttpStatus.CREATED).body(rentalCardOutputDto.get());
  }

  @ApiOperation(value = "대여기능", notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
  @PostMapping("rent")
  public ResponseEntity<RentalCardOutputDto> rentItem(
      @RequestBody UserItemInputDto userItemInputDto) {
    return ResponseEntity.ok(rentItemUsecase.rentItem(userItemInputDto));
  }

  @ApiOperation(value = "반납기능", notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
  @PostMapping("return")
  public ResponseEntity<RentalCardOutputDto> returnItem(
      @RequestBody UserItemInputDto userItemInputDto) {
    return ResponseEntity.ok(returnItemUsecase.returnItem(userItemInputDto));
  }

  @ApiOperation(value = "연체기능", notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
  @PostMapping("overdue")
  public ResponseEntity<RentalCardOutputDto> overdueItem(
      @RequestBody UserItemInputDto userItemInputDto) {
    return ResponseEntity.ok(overDueItemUsecase.overDueItem(userItemInputDto));
  }

  @ApiOperation(value = "연체해제기능", notes = "사용자정보,포인트 -> 도서카드정보 ")
  @PostMapping("clear-overdue")
  public ResponseEntity<RentalResultOutputDto> clearOverdue(
      @RequestBody ClearOverDueInfoDto clearOverDueInfoDto) {
    return ResponseEntity.ok(clearOverdueItemUsecase.clearOverdue(clearOverDueInfoDto));
  }
}
