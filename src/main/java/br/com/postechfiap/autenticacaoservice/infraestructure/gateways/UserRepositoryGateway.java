package br.com.postechfiap.autenticacaoservice.infraestructure.gateways;

import br.com.postechfiap.autenticacaoservice.application.gatways.UserGateway;
import br.com.postechfiap.autenticacaoservice.domain.enums.EmployeeTypeEnum;
import br.com.postechfiap.autenticacaoservice.infraestructure.persistence.entities.UserEntity;
import br.com.postechfiap.autenticacaoservice.infraestructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
        return userRepository.save(entity);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(UserEntity entity) {
        entity.delete();
        userRepository.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(entity);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserEntity> findByEmployeeType(EmployeeTypeEnum employeeType) {
        return userRepository.findByEmployeeTypeEnum(employeeType);
    }
}
