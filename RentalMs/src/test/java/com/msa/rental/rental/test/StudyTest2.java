package com.msa.rental.rental.test;

import com.msa.rental.rental.annotation.FastTest;
import com.msa.rental.rental.annotation.SlowTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class StudyTest2 {

  @FastTest
  void create_study() {
    System.out.println("StudyTest2.create_study");
  }

  @SlowTest
  void create_other_study() {
    System.out.println("StudyTest2.create_other_study");
  }

  @DisplayName("반복 테스트")
  @RepeatedTest(value = 10, name = "{displayName} 의 {currentRepetition} 번째 테스트")
  void repeat_study(RepetitionInfo r) {
    System.out.println("test => " + r.getCurrentRepetition() + " / " + r.getTotalRepetitions());
    Study study = new Study((int) (Math.random() * 100));
    System.out.println("study limit => " + study.getLimit());
  }

  @DisplayName("파라메터 정의해서 여러번 실행")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(strings = {"my", "name", "is", "tommy"})
  void parameterizeTest(String message) {
    System.out.println("message => " + message);
  }

  @DisplayName("파라메터 정의해서 여러번 실행")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(strings = {"my", "name", "is", "tommy"})
  // @EmptySource
  // @NullSource
  @NullAndEmptySource // message가 비어있는 혹은 null인 경우 테스트
  void parameterizeOtherTest(String message) {
    System.out.println("message => " + message);
  }

  @DisplayName("파라메터 study limit 로 정의해서 여러번 실행")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(ints = {10, 20, 30})
  void parameterizeStudyLimitTest(@ConvertWith(StudyConverter.class) Study study) {
    System.out.println("message => " + study.getLimit());
  }

  @DisplayName("파라메터 study limit,name 으로 정의해서 여러번 실행")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @CsvSource({"10,'tommy'", "20,'jiyoon'"})
  void parameterizeOtherStudyNameAndLimitTest(Integer limit, String name) {
    Study study = new Study(limit, name);
    System.out.println("message => " + study.toString());
  }

  @DisplayName("파라메터 study limit,name 으로 정의해서 여러번 실행")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @CsvSource({"10,'tommy'", "20,'jiyoon'"})
  void parameterizeOtherStudyNameAndLimitArgumentAccessorTest(ArgumentsAccessor argumentsAccessor) {
    Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
    System.out.println("message => " + study.toString());
  }

  @DisplayName("파라메터 study limit,name 으로 정의해서 여러번 실행")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @CsvSource({"30,'tommy'", "40,'jiyoon'"})
  void parameterizeOtherStudyNameAndLimitAggregatorTest(
      @AggregateWith(StudyAggregator.class) Study study) {
    System.out.println("message => " + study.toString());
  }

  // static 이나 public 으로 만들어야만 aggregator 를 사용할 수 있음
  static class StudyAggregator implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
        throws ArgumentsAggregationException {
      return new Study(accessor.getInteger(0), accessor.getString(1));
    }
  }

  static class StudyConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType)
        throws ArgumentConversionException {
      return new Study(Integer.parseInt(source.toString()));
    }
  }
}
