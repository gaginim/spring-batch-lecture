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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HelloJobConfiguration {

  private final String JOB_NAME = "myJob";
  private final String STEP1_NAME = JOB_NAME + "_step1";
  private final String STEP2_NAME = JOB_NAME + "_step2";
  private final String TASK1_NAME = STEP1_NAME + "_task1";

  @Bean(JOB_NAME)
  public Job myJob(
      JobRepository jobRepository,
      @Qualifier(STEP1_NAME) Step myStep,
      @Qualifier(STEP2_NAME) Step myNextStep) {
    return new JobBuilder("myJob", jobRepository).start(myStep).next(myNextStep).build();
  }

  @Bean(STEP1_NAME)
  public Step myStep(
      JobRepository jobRepository,
      @Qualifier(TASK1_NAME) Tasklet myTasklet,
      PlatformTransactionManager transactionManager) {
    return new StepBuilder(STEP1_NAME, jobRepository)
        .tasklet(myTasklet, transactionManager)
        .build();
  }

  @Bean(STEP2_NAME)
  public Step myNextStep(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {

    return new StepBuilder(STEP2_NAME, jobRepository)
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

  @Bean(TASK1_NAME)
  public Tasklet myTasklet() {
    return (contribution, chunkContext) -> {
      System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
      System.out.println(" >>> hello tommy ");
      System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
      return RepeatStatus.FINISHED;
    };
  }
}
