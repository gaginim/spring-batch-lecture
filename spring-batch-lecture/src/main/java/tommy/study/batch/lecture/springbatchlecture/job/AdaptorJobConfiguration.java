package tommy.study.batch.lecture.springbatchlecture.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import tommy.study.batch.lecture.springbatchlecture.service.ItemReaderService;

@Configuration
@RequiredArgsConstructor
public class AdaptorJobConfiguration {

  private final String JOB_NAME = "adaptorJob";
  private final String STEP1_NAME = JOB_NAME + "_step1";

  @Bean(JOB_NAME)
  public Job adaptorJob(JobRepository jobRepository, @Qualifier(STEP1_NAME) Step myStep) {
    return new JobBuilder("adaptorJob", jobRepository).start(myStep).build();
  }

  @Bean(STEP1_NAME)
  public Step myStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder(STEP1_NAME, jobRepository)
        .chunk(10, transactionManager)
        .reader(
            new ItemReader<String>() {
              int i = 0;
              @Override
              public String read()
                  throws Exception,
                      UnexpectedInputException,
                      ParseException,
                      NonTransientResourceException {

                i++;
                return i > 35 ? null : "item" + i;
              }
            })
        .writer(customItemWriter())
        .build();
  }

//  public ItemReaderAdapter customItemReaderAdaptorItem() {
//    ItemReaderAdapter readerAdapter = new ItemReaderAdapter();
//    readerAdapter.setTargetObject(ItemReaderService.class);
//    readerAdapter.setTargetMethod("introduce");
//    return readerAdapter;
//  }

//  @Bean
//  public ItemWriter<ItemReaderService> itemReaderAdapterCustomItemWriter() {
//    return items -> {
//      System.out.println(items.getItems());
//    };
//  }

  public ItemWriter customItemWriter() {
    ItemWriterAdapter<String> adapter = new ItemWriterAdapter();
    adapter.setTargetObject(itemReaderService());
    adapter.setTargetMethod("introduce");
    return adapter;
  }

  public ItemReaderService itemReaderService() {
    return new ItemReaderService();
  }
}
