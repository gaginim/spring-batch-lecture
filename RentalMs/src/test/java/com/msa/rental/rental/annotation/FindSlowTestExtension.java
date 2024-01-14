package com.msa.rental.rental.annotation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

public class FindSlowTestExtension
    implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

  private long THREHOLD = 1000L;

  public FindSlowTestExtension(long l) {
    THREHOLD = l;
  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    String testClassName = context.getRequiredTestClass().getName();
    SlowTest annotation = context.getRequiredTestMethod().getAnnotation(SlowTest.class);
    String testMethodName = context.getRequiredTestMethod().getName();
    Store store = context.getStore(Namespace.create(testClassName, testMethodName));
    LocalDateTime startTime = store.remove("START_TIME", LocalDateTime.class);

    long duration = Math.abs(ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));

    if (THREHOLD < duration && annotation == null)
      System.out.println(testMethodName + " method 는 slowTest annotation 사용할 대상입니다.");
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {
    String testClassName = context.getRequiredTestClass().getName();
    String testMethodName = context.getRequiredTestMethod().getName();
    Store store = context.getStore(Namespace.create(testClassName, testMethodName));

    store.put("START_TIME", LocalDateTime.now());
  }
}
