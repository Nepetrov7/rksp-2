package ru.petrov.gate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FeignClientConfig {
  @Bean
  public SomeStudentApi someStudentApi() {
    ApiClient apiClient = new ApiClient();
    apiClient.setBasePath("http://localhost:8083"); // или data.service.url
    return apiClient.buildClient(SomeStudentApi.class);
  }
}
