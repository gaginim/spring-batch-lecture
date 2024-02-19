package tommy.study.batch.lecture.springbatchlecture.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableJdbcRepositories(
    basePackages = {"tommy.study.batch.lecture.springbatchlecture.**.repository"})
@EntityScan(basePackages = "tommy.study.batch.lecture.springbatchlecture.**.entity")
public class JdbcConfiguration extends AbstractJdbcConfiguration {

  @Bean("jdbcHikariConfig")
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public HikariConfig jdbcHikariConfig() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setUsername("tommy");
    hikariConfig.setPassword("password");
    return hikariConfig;
  }

  @Bean("jdbcDataSource")
  public DataSource jdbcDataSource(
      @Qualifier("jdbcHikariConfig") final HikariConfig jdbcHikariConfig) {
    return new HikariDataSource(jdbcHikariConfig);
  }

  @Bean("jdbcClient")
  public JdbcClient jdbcClient(@Qualifier("jdbcDataSource") DataSource dataSource) {
    return JdbcClient.create(dataSource);
  }

  @Bean("namedParameterJdbcOperations")
  NamedParameterJdbcOperations namedParameterJdbcOperations(
      @Qualifier("jdbcDataSource") final DataSource jdbcDataSource) {
    return new NamedParameterJdbcTemplate(jdbcDataSource);
  }

  @Bean("jdbcTemplate")
  JdbcTemplate jdbcTemplate(@Qualifier("jdbcDataSource") final DataSource jdbcDataSource) {
    return new JdbcTemplate(jdbcDataSource);
  }

  @Bean
  public JdbcTransactionManager transactionManager(
      @Qualifier("jdbcDataSource") final DataSource jdbcDataSource) {
    return new JdbcTransactionManager(jdbcDataSource);
  }
}
