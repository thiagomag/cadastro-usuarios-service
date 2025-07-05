package br.com.postechfiap.cadastrousuarioservice.infraestructure.persistence.repository;

import br.com.postechfiap.cadastrousuarioservice.domain.enums.EmployeeTypeEnum;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByEmployeeType(EmployeeTypeEnum employeeTypeEnum);

}