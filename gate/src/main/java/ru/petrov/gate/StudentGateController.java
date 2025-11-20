package ru.petrov.gate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.petrov.data.api.StudentGateApi;
import ru.petrov.data.model.StudentGateCreateRequest;
import ru.petrov.data.model.StudentGateResponse;
import ru.petrov.gate.client.api.StudentDataApi;
import ru.petrov.gate.client.model.StudentDataCreateRequest;
import ru.petrov.gate.client.model.StudentDataResponse;

@RestController
@RequiredArgsConstructor
public class StudentGateController implements StudentGateApi {

  private final StudentDataApi studentsFeignClient;

  /**
   * Проксирует создание студента во внутренний DATA-SERVICE.
   * Получает запрос от клиента, преобразует модель и перенаправляет.
   */
  @Override
  public ResponseEntity<StudentGateResponse> createStudent(StudentGateCreateRequest request) {
    StudentDataCreateRequest dataRequest = new StudentDataCreateRequest();
    dataRequest.setFullName(request.getFullName());
    dataRequest.setPassport(request.getPassport());

    StudentDataResponse dataResponse = studentsFeignClient.createStudentDataInData(dataRequest);

    StudentGateResponse gateResponse = new StudentGateResponse();
    gateResponse.setId(dataResponse.getId());
    gateResponse.setFullName(dataResponse.getFullName());
    gateResponse.setPassport(dataResponse.getPassport());

    return ResponseEntity.status(201).body(gateResponse);
  }
}
