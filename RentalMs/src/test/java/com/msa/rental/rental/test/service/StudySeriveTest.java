package com.msa.rental.rental.test.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.msa.rental.rental.test.Member;
import com.msa.rental.rental.test.Study;
import com.msa.rental.rental.test.repository.StudyRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudySeriveTest {
  @Mock MemberServie memberServie; // 이렇게만 한다고 해서 되는게 아니라 ExtendWith 를 사용해야 함
  @Mock StudyRepository repository;
  private StudySerive studySerive;

  @BeforeEach
  void init() {
    studySerive = new StudySerive(memberServie, repository);
  }

  @Test
  @DisplayName("이렇게 직접 mock 을 사용하는 방법도 있다는걸 보여주기 위함")
  void create_study_test001() {
    StudyRepository studyRepository = Mockito.mock(StudyRepository.class);
    MemberServie memberServie1 = Mockito.mock(MemberServie.class);

    StudySerive studySerive1 = new StudySerive(memberServie1, studyRepository);
    Assertions.assertAll(() -> Assertions.assertNotNull(studySerive1));
  }

  @Test
  @DisplayName("실제로 많이 사용하는 방법")
  void create_study_test002() {
    // Given
    Study study = new Study(100);

    when(memberServie.findById(any())).thenReturn(Optional.of("tommy")); // stubbing
    when(repository.save(any())).thenReturn(study);

    given(memberServie.findById(any())).willReturn(Optional.of("tommy"));
    given(repository.save(any())).willReturn(study);

    // When
    Study result = studySerive.ofCreateStudy(1L, study);

    // Then
    Assertions.assertAll(
        () -> Assertions.assertNotNull(studySerive),
        () -> Assertions.assertNotNull(result),
        () -> Assertions.assertEquals("tommy", result.getName()));
  }

  @Test
  @DisplayName("이렇게 매게변수로도 사용할 수 있음")
  void create_study_test003(
      @Mock MemberServie localMemberService, @Mock StudyRepository localStudyRepository) {
    StudySerive localStudyService = new StudySerive(localMemberService, localStudyRepository);

    Assertions.assertAll(() -> Assertions.assertNotNull(localStudyService));
  }

  @Test
  @DisplayName("doThrow 사용해보기")
  void create_study_test004() {
    Study study = new Study(100);

    when(memberServie.findById(any())).thenReturn(Optional.of("tommy")); // stubbing
    doThrow(new IllegalArgumentException())
        .when(repository)
        .save(study); // repository.save method 호출하면 IllegalArgumentException 오류 발생하게끔 처리

    Assertions.assertThrows(
        IllegalArgumentException.class, () -> studySerive.ofCreateStudy(1L, study));
  }

  @Test
  @DisplayName("테스트를 한꺼번에 처리하는 방법")
  void create_study_test005() {
    Study study = new Study(100);

    when(memberServie.findById(any()))
        .thenReturn(Optional.of("tommy")) // 첫번째는 tommy 라고 stubbing
        .thenThrow(new IllegalArgumentException()) // 두번째는 오류내기
        .thenReturn(Optional.empty()); // 세번째 테스트는 비어있기

    Assertions.assertEquals("tommy", memberServie.findById(1L).get());

    Assertions.assertThrows(IllegalArgumentException.class, () -> memberServie.findById(any()));

    Assertions.assertEquals(Optional.empty(), memberServie.findById(1L));
  }

  @Test
  @DisplayName("verify 를 이용해서 호출되었는지를 검증하는 예제-에러나는데 확인 필요")
  void create_study_test006() {
    Study study = new Study(100);
    Member member = new Member(1L, "tommy");

    when(memberServie.findById(any())).thenReturn(Optional.of("tommy")); // stubbing
    when(repository.save(any())).thenReturn(study);

    Study result = studySerive.ofCreateStudy(1L, study);

    Assertions.assertAll(
        () -> Assertions.assertNotNull(studySerive),
        () -> Assertions.assertNotNull(result),
        () -> Assertions.assertEquals("tommy", result.getName()));

    verify(memberServie, times(1)).notify(study); // notify 라는 method 를 1번(times) 은 호출되어야 한다는 의미
    verify(memberServie, times(1)).alarm(member);

    InOrder inOrder = inOrder(memberServie); // memberService 에서 각 메서드가 순차적으로 진행해야 하는걸 검증
    inOrder.verify(memberServie).notify(study);
    inOrder.verify(memberServie).alarm(member);

    verifyNoMoreInteractions(memberServie); // 더이상 어떤 호출도 발생하면 안된다는 의미
  }

  @Test
  @DisplayName("BDD Style (create_study_test002 와 비교해서 보렴)")
  void create_study_test007() {
    // Given
    Study study = new Study(100);

    given(memberServie.findById(any())).willReturn(Optional.of("tommy"));
    given(repository.save(any())).willReturn(study);

    // When
    Study result = studySerive.ofCreateStudy(1L, study);

    // Then
    then(memberServie).should(times(1)).notify(study);
    then(memberServie).shouldHaveNoMoreInteractions();
  }
}
