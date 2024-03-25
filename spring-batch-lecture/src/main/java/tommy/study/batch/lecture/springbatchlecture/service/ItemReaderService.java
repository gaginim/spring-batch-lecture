package tommy.study.batch.lecture.springbatchlecture.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ItemReaderService<T> {

  public T introduce(T item) {
    System.out.println("introduce => " + item);
    return item;
  }
}
