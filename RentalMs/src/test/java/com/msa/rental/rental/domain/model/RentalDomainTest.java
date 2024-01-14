package com.msa.rental.rental.domain.model;

import com.msa.rental.rental.domain.model.vo.IDName;
import com.msa.rental.rental.domain.model.vo.Item;
import com.msa.rental.rental.domain.model.vo.RentStatus;
import com.msa.rental.rental.domain.model.vo.RentalItem;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RentalDomainTest {
  private RentalCard rentalCard;
  private List<Item> items;

  @BeforeAll
  void init() {
    rentalCard = RentalCard.of(IDName.of("wanchub429", "hanjiyoon"));
    items = List.of(Item.of(1, "도서1"), Item.of(2, "도서2"));
  }

  @Test
  @Order(1)
  @DisplayName("1. 첫번째 도서를 대여한다.")
  void rental_domain_test001() {
    rentalCard.rentalItem(items.get(0));
    showRentalCard();

    System.out.println("RentalServiceTest.rental_domain_test001 => " + rentalCard.getRentalItems());

    Assertions.assertAll(
        () -> Assertions.assertNotNull(rentalCard),
        () -> Assertions.assertEquals("wanchub429", rentalCard.getMember().getId()),
        () -> Assertions.assertEquals("hanjiyoon", rentalCard.getMember().getName()),
        () -> Assertions.assertEquals(1, rentalCard.getRentalItems().size()));
  }

  @Test
  @Order(2)
  @DisplayName("2. 두번째 도서를 대여한다.")
  void rental_domain_test002() {
    rentalCard.rentalItem(items.get(1));
    showRentalCard();

    Assertions.assertAll(
        () -> Assertions.assertNotNull(rentalCard),
        () -> Assertions.assertEquals("wanchub429", rentalCard.getMember().getId()),
        () -> Assertions.assertEquals("hanjiyoon", rentalCard.getMember().getName()),
        () -> Assertions.assertEquals(2, rentalCard.getRentalItems().size()));
  }

  @Test
  @Order(3)
  @DisplayName("3. 첫번째 도서를 반납한다.")
  void rental_domain_test003() {
    rentalCard.returnItem(items.get(0), LocalDateTime.now());
    showRentalCard();

    Assertions.assertAll(
        () -> Assertions.assertNotNull(rentalCard),
        () -> Assertions.assertEquals("wanchub429", rentalCard.getMember().getId()),
        () -> Assertions.assertEquals("hanjiyoon", rentalCard.getMember().getName()),
        () -> Assertions.assertEquals(1, rentalCard.getRentalItems().size()));
  }

  @Test
  @Order(4)
  @DisplayName("4. 두번째 도서가 연체되어 대여상태가 안되며 연체료가 쌓였다.")
  void rental_domain_test004() {
    rentalCard.overdueItem(items.get(1));
    showRentalCard();

    Assertions.assertAll(
        () -> Assertions.assertNotNull(rentalCard),
        () -> Assertions.assertEquals("wanchub429", rentalCard.getMember().getId()),
        () -> Assertions.assertEquals("hanjiyoon", rentalCard.getMember().getName()),
        () -> Assertions.assertEquals(RentStatus.UNAVAILABLE, rentalCard.getStatus()),
        () -> Assertions.assertEquals(true, rentalCard.getRentalItems().get(0).isOverdue()));
  }

  @Test
  @Order(5)
  @DisplayName("5. 두번째 도서가 연체되었다.")
  void rental_domain_test005() {
    rentalCard.returnItem(items.get(1), LocalDateTime.now());
    showRentalCard();

    Assertions.assertAll(
        () -> Assertions.assertNotNull(rentalCard),
        () -> Assertions.assertEquals("wanchub429", rentalCard.getMember().getId()),
        () -> Assertions.assertEquals("hanjiyoon", rentalCard.getMember().getName()),
        () -> Assertions.assertEquals(RentStatus.UNAVAILABLE, rentalCard.getStatus()),
        () -> Assertions.assertEquals(0, rentalCard.getRentalItems().size()),
        () -> Assertions.assertEquals(2, rentalCard.getReturnItems().size()),
        () -> Assertions.assertTrue(rentalCard.getLateFee().getPoint() > 0));
  }

  @Test
  @Order(6)
  @DisplayName("6. 연체료가 쌓인만큼 포인트를 지급했더니 정사 상태로 돌아왔다.")
  void rental_domain_test006() {
    rentalCard.changeRentalAvailable(rentalCard.getLateFee().getPoint());
    showRentalCard();

    Assertions.assertAll(
        () -> Assertions.assertNotNull(rentalCard),
        () -> Assertions.assertEquals("wanchub429", rentalCard.getMember().getId()),
        () -> Assertions.assertEquals("hanjiyoon", rentalCard.getMember().getName()),
        () -> Assertions.assertEquals(RentStatus.AVAILABLE, rentalCard.getStatus()),
        () -> Assertions.assertEquals(0, rentalCard.getRentalItems().size()),
        () -> Assertions.assertEquals(2, rentalCard.getReturnItems().size()),
        () -> Assertions.assertTrue(rentalCard.getLateFee().getPoint() == 0));
  }

  private void showRentalCard() {
    System.out.println("================================");
    System.out.println(this.rentalCard.getMember().getName() + " 도서카드 ");
    System.out.println(
        " 대여도서 연체상태 : "
            + this.rentalCard.getRentalItems().stream().map(RentalItem::isOverdue).toList());
    System.out.println("총연체료 : " + this.rentalCard.getLateFee().getPoint());
    System.out.println("대여가능여부 : " + this.rentalCard.getStatus().name());
    System.out.print("대여목록 : ");
    System.out.print(
        this.rentalCard.getRentalItems().stream().map(m -> m.getItem().getTitle()).toList());
    System.out.println("");
    System.out.print("반납목록 : ");
    System.out.print(
        this.rentalCard.getRentalItems().stream().map(m -> m.getItem().getTitle()).toList());
  }
}
