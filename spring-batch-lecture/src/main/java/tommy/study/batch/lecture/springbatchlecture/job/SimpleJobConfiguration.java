package tommy.study.batch.lecture.springbatchlecture.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import tommy.study.batch.lecture.springbatchlecture.validator.SimpleJobCustomParameterValidator;

@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration {
  private final String JOB_NAME = "mySimpleJob";
  private final String STEP1_NAME = JOB_NAME + "_step1";
  private final String STEP2_NAME = JOB_NAME + "_step2";
  private final String STEP3_NAME = JOB_NAME + "_step3";

  private final SimpleJobCustomParameterValidator simpleJobCustomParameterValidator;

  @Bean(JOB_NAME)
  public Job job(
      JobRepository jobRepository,
      @Qualifier(STEP1_NAME) Step step1,
      @Qualifier(STEP2_NAME) Step step2,
      @Qualifier(STEP3_NAME) Step step3) {
    return new JobBuilder("mySimpleJob", jobRepository)
        .start(step1)
        .next(step2)
        .next(step3)
        .incrementer(new RunIdIncrementer())
        //        .validator(simpleJobCustomParameterValidator)
        .validator(
            new DefaultJobParametersValidator(
                new String[] {"name", "date"}, new String[] {"count"}))
        .preventRestart()
        .listener(
            new JobExecutionListener() {
              @Override
              public void beforeJob(JobExecution jobExecution) {
                JobExecutionListener.super.beforeJob(jobExecution);
              }
            })
        .build();
  }

  @Bean(STEP1_NAME)
  public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder(STEP1_NAME, jobRepository)
        .tasklet(
            new Tasklet() {
              @Override
              public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
                  throws Exception {
                // debug 중지 테스가
                //
                // chunkContext.getStepContext().getStepExecution().setStatus(BatchStatus.FAILED);
                //                  contribution.setExitStatus(ExitStatus.STOPPED);

                System.out.println("step1 has executed");
                return RepeatStatus.FINISHED;
              }
            },
            transactionManager)
        .build();
  }

  @Bean(STEP2_NAME)
  public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder(STEP1_NAME, jobRepository)
        .tasklet(
            new Tasklet() {
              @Override
              public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
                  throws Exception {
                System.out.println("step2 has executed");
                return RepeatStatus.FINISHED;
              }
            },
            transactionManager)
        .build();
  }

  @Bean(STEP3_NAME)
  public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder(STEP1_NAME, jobRepository)
        .tasklet(
            new Tasklet() {
              @Override
              public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
                  throws Exception {
                System.out.println("step3 has executed");
                return RepeatStatus.FINISHED;
              }
            },
            transactionManager)
        .build();
  }
}
