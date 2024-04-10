package tommy.study.batch.lecture.springbatchlecture.job;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBatchTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SimpleJobConfigurationTest {

  private JobLauncherTestUtils jobLauncherTestUtils;

  @Qualifier("mySimpleJob")
  @Autowired
  private Job job;

  @Autowired private JobLauncher jobLauncher;

  @Autowired private JobRepository jobRepository;

  @BeforeAll
  public void before() {
    this.jobLauncherTestUtils = new JobLauncherTestUtils();
    this.jobLauncherTestUtils.setJobLauncher(jobLauncher);
    this.jobLauncherTestUtils.setJobRepository(jobRepository);
    this.jobLauncherTestUtils.setJob(job);
  }

  private JobParameters defaultJobParameters() {
    JobParametersBuilder paramsBuilder = new JobParametersBuilder();
    return paramsBuilder.toJobParameters();
  }

  @Test
  void test01_flavor_snapshot_job() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());

    assertAll(() -> assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus()));
  }
}
