// package com.msa.rental.rental.test.service;
//
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
//
// import com.msa.rental.rental.test.Study;
// import com.msa.rental.rental.test.repository.StudyRepository;
// import java.util.Optional;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;
// import org.testcontainers.containers.PostgreSQLContainer;
// import org.testcontainers.junit.jupiter.Container;
// import org.testcontainers.junit.jupiter.Testcontainers;
//
// @SpringBootTest
// @ExtendWith(MockitoExtension.class)
// @ActiveProfiles("test")
// @Testcontainers
// public class StudyServiceOtherTest {
//  @Mock MemberServie memberServie;
//  @Autowired StudyRepository studyRepository;
//
//  @Container // container annotation 사용하려면 static 으로 정의해야 함. 안그러면 각 method 마다 정의해야함
//  static PostgreSQLContainer postgreSQLContainer =
//      new PostgreSQLContainer().withDatabaseName("studytest");
//
//  @BeforeEach
//  void init() {
//    studyRepository.deleteAll();
//  }
//
//  //  @BeforeAll
//  //  static void init() {
//  //    postgreSQLContainer.start();
//  //    System.out.println("url => " + postgreSQLContainer.getJdbcUrl());
//  //  }
//  //
//  //  @AfterAll
//  //  static void finish() {
//  //    postgreSQLContainer.stop();
//  //  }
//
//  @Test
//  @DisplayName("test container (postgreSql) 를 띄워서 확인")
//  void create_study_test001() {
//
//    // Given
//    Study study = new Study(4000, "-");
//    StudySerive studyService = new StudySerive(memberServie, studyRepository);
//
//    when(memberServie.findById(any())).thenReturn(Optional.of("tommy")); // stubbing
//
//    // When
//    Study result = studyService.ofCreateStudy(1L, study);
//
//    // Then
//    Assertions.assertAll(
//        () -> Assertions.assertNotNull(studyService),
//        () -> Assertions.assertNotNull(result),
//        () -> Assertions.assertEquals("tommy", result.getName()));
//  }
// }
