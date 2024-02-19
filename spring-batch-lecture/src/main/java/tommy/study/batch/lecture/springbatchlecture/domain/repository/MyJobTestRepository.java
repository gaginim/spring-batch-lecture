package tommy.study.batch.lecture.springbatchlecture.domain.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import tommy.study.batch.lecture.springbatchlecture.domain.entity.MyJobTest;

@Repository
public interface MyJobTestRepository extends ListCrudRepository<MyJobTest, Long> {}
