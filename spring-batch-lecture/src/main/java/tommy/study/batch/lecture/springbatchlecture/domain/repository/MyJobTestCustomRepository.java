package tommy.study.batch.lecture.springbatchlecture.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import tommy.study.batch.lecture.springbatchlecture.domain.dto.MyJobTestDto;

@Repository
@RequiredArgsConstructor
public class MyJobTestCustomRepository {
  private final JdbcClient jdbcClient;

  public int create(final MyJobTestDto.CreateDto createDto) {

    String sql =
        """
        INSERT INTO my_job_test(name, created_at, created_by)
        VALUES(?, ?, ?)
        """;

    return jdbcClient
        .sql(sql)
        .param(createDto.getName())
        .param(createDto.getCreatedAt())
        .param(createDto.getCreatedBy())
        .update();
  }
}
