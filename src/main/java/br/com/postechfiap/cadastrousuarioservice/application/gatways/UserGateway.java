package br.com.postechfiap.cadastrousuarioservice.application.gatways;

import br.com.postechfiap.cadastrousuarioservice.domain.enums.EmployeeTypeEnum;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.persistence.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserGateway extends BaseGateway<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByEmployeeType(EmployeeTypeEnum employeeType);
}
