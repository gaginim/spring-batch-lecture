package tommy.study.batch.lecture.springbatchlecture;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchLectureApplication {

  public static void main(String[] args) {
    System.out.println("test tommy batch");
    SpringApplication.run(SpringBatchLectureApplication.class, args);
  }
}
