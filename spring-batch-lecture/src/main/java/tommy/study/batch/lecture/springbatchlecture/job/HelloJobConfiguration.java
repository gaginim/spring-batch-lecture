package tommy.study.batch.lecture.springbatchlecture.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HelloJobConfiguration {

  @Bean
  public Job myJob(JobRepository jobRepository, Step myStep, Step myNextStep) {
    return new JobBuilder("myJob", jobRepository).start(myStep).next(myNextStep).build();
  }

  @Bean
  public Step myStep(
      JobRepository jobRepository,
      Tasklet myTasklet,
      PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep", jobRepository).tasklet(myTasklet, transactionManager).build();
  }

  @Bean
  public Step myNextStep(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {

    return new StepBuilder("myNextStep", jobRepository)
        .tasklet(
            new Tasklet() {
              @Override
              public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
                  throws Exception {
                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
                System.out.println(" >>> next work tommy");
                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
                return RepeatStatus.FINISHED;
              }
            },
            transactionManager)
        .build();
  }

  @Bean
  public Tasklet myTasklet() {
    return (contribution, chunkContext) -> {
      System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
      System.out.println(" >>> hello tommy ");
      System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
      return RepeatStatus.FINISHED;
    };
  }
}
