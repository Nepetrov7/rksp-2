package ru.petrov.gate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.petrov.gate.client.api.StudentDataApi;
import ru.petrov.gate.client.ApiClient;

@Configuration
public class FeignClientConfig {
  @Bean
  public StudentDataApi studentDataApi() {
    ApiClient apiClient = new ApiClient();
    apiClient.setBasePath("http://localhost:8083"); // или data.service.url
    return apiClient.buildClient(StudentDataApi.class);
  }
}
