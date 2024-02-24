package tommy.study.batch.lecture.springbatchlecture.validator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Configuration
public class SimpleJobCustomParameterValidator implements JobParametersValidator {

  private final List<String> requiredKeys;

  public SimpleJobCustomParameterValidator() {
    this.requiredKeys = List.of("name", "date");
  }

  @Override
  public void validate(JobParameters parameters) throws JobParametersInvalidException {

    for (String key : requiredKeys) {
      if (ObjectUtils.isEmpty(parameters.getString(key)))
        throw new JobParametersInvalidException("name parameter is required");
    }
  }
}
