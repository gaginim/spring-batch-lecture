package tommy.study.batch.lecture.springbatchlecture.increments;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class SimpleJobParameterIncrement implements JobParametersIncrementer {

  @Override
  public JobParameters getNext(JobParameters parameters) {
    return new JobParametersBuilder(parameters)
        .addString("tommyParam", UUID.randomUUID().toString())
        .toJobParameters();
  }
}
