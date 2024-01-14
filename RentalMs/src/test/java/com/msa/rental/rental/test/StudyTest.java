package com.msa.rental.rental.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

// @DisplayNameGeneration(ReplaceUnderscores.class) // class, method 모두 사용 가능. ReplaceUnderscores 는
// snake 표기의 _ 를 공백으로 치환
public class StudyTest {

  @Test
  @DisplayName("테스트 입니다.") // 이걸 더 권장
  @EnabledOnOs(OS.MAC) // 이렇게 처리할 수도 있음
  void create_new_study() {

    // 이 환경변수의 값이 맞는 경우에만 에러 테스트 진행함
    assumeTrue("local".equalsIgnoreCase(System.getenv("test_env")));

    // test_env 의 환경변수가 local 일 경우에만 테스트를 실행한다.
    assumingThat(
        "local".equalsIgnoreCase(System.getenv("test_env")),
        () -> {
          System.out.println("assuming local test");
        });

    // test_env 의 환경변수가 tommy 일 경우에만 테스트를 실행한다.
    assumingThat(
        "tommy".equalsIgnoreCase(System.getenv("test_env")),
        () -> {
          System.out.println("assuming tommy test");
        });

    Study study = new Study(10);

    // 에러 발싱 문자열 기입 시, "문자열"을 쓰는것보다 () -> "문자열" 로 해서 처리하는게 더 좋아.
    // "문자열" 만 쓰면 이 에러내용이 성공이든 실패이든 연산을 함
    // 그런데 () -> "문자열" 을 쓰면 실패했을 때만 문자열이 실행되서 비용, 성능에 유리

    // assertAll 를 쓰면 안에 있는 에러 중 에러가 발생하더라도 다음 assert 문 실행
    assertAll(
        () -> assertNotNull(study),
        () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 draft여야 한다."),
        () -> assertTrue(study.getLimit() > 0, "스터디 최소 인원은 0보다 커야 한다."));
  }

  @Test
  @DisplayName("환경변수 테스트")
  @EnabledIfEnvironmentVariable(named = "test_env", matches = "local")
  void create_new_study_check_enable_env() {
    Study study = new Study(10);
    assertAll(
        () -> assertNotNull(study),
        () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 draft여야 한다."),
        () -> assertTrue(study.getLimit() > 0, "스터디 최소 인원은 0보다 커야 한다."));
  }

  @Test
  @DisplayName("에러 발생 시 어떻게 출력하는지 확인")
  void error_minus_limit() {
    String message =
        assertThrows(IllegalArgumentException.class, () -> new Study(-100)).getMessage();
    assertEquals("cannot register minus value", message);
  }

  @Test
  @DisplayName("타임아웃 에러 발생 시 어떻게 출력되는지 확인")
  void timeout_test() {
    assertTimeout(
        Duration.ofMillis(100),
        () -> {
          new Study(10);
          //      Thread.sleep(300);
        });
  }

  @Disabled // 사용하지 않을 경우
  @Test
  void create_other() {
    System.out.println("StudyTest.create_other");
    Study study = new Study(10);
    assertNotNull(study);
  }

  @BeforeAll
  static void init() {
    System.out.println("StudyTest.init");
    //    System.out.println("처음 한번만 호출됨");
    //    System.out.println("무조건 static 으로 생성해야 함");
  }

  @AfterAll
  static void end() {
    System.out.println("StudyTest.end");
    //    System.out.println("처음 한번만 호출됨");
    //    System.out.println("무조건 static 으로 생성해야 함");
  }

  @BeforeEach
  void initAll() {
    System.out.println("StudyTest.initAll");
    //    System.out.println("모든 메서드 실행 전 무조건 1번 호출");
  }

  @AfterEach
  void endAll() {
    System.out.println("StudyTest.endAll");
    //    System.out.println("모든 메서드 실행 후 무조건 1번 호출");
  }
}
