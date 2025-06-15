package br.com.postechfiap.autenticacaoservice.application.gatways;

import br.com.postechfiap.autenticacaoservice.domain.enums.EmployeeTypeEnum;
import br.com.postechfiap.autenticacaoservice.infraestructure.persistence.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserGateway extends BaseGateway<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByEmployeeType(EmployeeTypeEnum employeeType);
}
