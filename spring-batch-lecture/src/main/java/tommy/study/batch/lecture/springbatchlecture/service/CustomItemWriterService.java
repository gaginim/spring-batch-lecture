package tommy.study.batch.lecture.springbatchlecture.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomItemWriterService<T> {

  public T print(T item) {
    System.out.println("output => " + item);
    return item;
  }
}
