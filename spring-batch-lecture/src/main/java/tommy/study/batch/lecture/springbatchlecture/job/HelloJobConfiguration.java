package tommy.study.batch.lecture.springbatchlecture.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HelloJobConfiguration {

  @Bean
  public Job myJob(JobRepository jobRepository, Step myStep) {
    return new JobBuilder("myJob", jobRepository).start(myStep).build();
  }

  @Bean
  public Step myStep(
      JobRepository jobRepository,
      Tasklet myTasklet,
      PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep", jobRepository).tasklet(myTasklet, transactionManager).build();
  }

  @Bean
  public Tasklet myTasklet() {
    return (contribution, chunkContext) -> {
      System.out.println(" >>> hello tommy ");
      return RepeatStatus.FINISHED;
    };
  }
}
