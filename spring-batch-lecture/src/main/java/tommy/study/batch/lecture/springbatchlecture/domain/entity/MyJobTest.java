package tommy.study.batch.lecture.springbatchlecture.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("my_job_test")
public class MyJobTest {

  @CreatedDate
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  protected LocalDateTime createdAt;

  @CreatedBy protected String createdBy;
  @Id private Long id;
  private String name;

  @Builder
  private MyJobTest(String name, LocalDateTime createdAt, String createdBy) {
    this.name = name;
    this.createdAt = createdAt;
    this.createdBy = createdBy;
  }
}
