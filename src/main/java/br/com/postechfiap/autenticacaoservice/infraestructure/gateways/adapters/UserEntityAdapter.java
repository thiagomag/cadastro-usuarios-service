package br.com.postechfiap.autenticacaoservice.infraestructure.gateways.adapters;

import br.com.postechfiap.autenticacaoservice.application.configuration.SecurityConfiguration;
import br.com.postechfiap.autenticacaoservice.domain.entity.Role;
import br.com.postechfiap.autenticacaoservice.domain.entity.User;
import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.UserResponse;
import br.com.postechfiap.autenticacaoservice.infraestructure.persistence.entities.RoleEntity;
import br.com.postechfiap.autenticacaoservice.infraestructure.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserEntityAdapter {

    private final SecurityConfiguration securityConfiguration;

    public UserEntity toUserEntity(User user, RoleEntity role) {
        return UserEntity.builder()
                .email(user.getEmail())
                .password(securityConfiguration.passwordEncoder().encode(user.getPassword()))
                .name(user.getFullName())
                .employeeType(user.getEmployeeTypeEnum())
                .councilNumber(user.getCouncilNumber())
                .councilState(user.getCouncilState())
                .specialty(user.getSpecialty())
                .roleEntity(role)
                .build();
    }

    public UserEntity updateUserEntity(UserEntity userEntity, User user, RoleEntity role) {
        return UserEntity.builder()
                .id(userEntity.getId())
                .email(user.isEmailChangeAuthorized() && user.getEmail() != null ? user.getEmail() : userEntity.getEmail())
                .password(user.isPasswordChangeAuthorized() && user.getPassword() != null ? securityConfiguration.passwordEncoder().encode(user.getPassword()) : userEntity.getPassword())
                .name(Optional.ofNullable(user.getFullName()).orElse(user.getFullName()))
                .employeeType(Optional.ofNullable(user.getEmployeeTypeEnum()).orElse(userEntity.getEmployeeType()))
                .councilNumber(Optional.ofNullable(user.getCouncilNumber()).orElse(userEntity.getCouncilNumber()))
                .councilState(Optional.ofNullable(user.getCouncilState()).orElse(userEntity.getCouncilState()))
                .specialty(Optional.ofNullable(user.getSpecialty()).orElse(userEntity.getSpecialty()))
                .roleEntity(Optional.ofNullable(role).orElse(userEntity.getRoleEntity()))
                .build();
    }

    public UserResponse toUserResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .employeeTypeEnum(user.getEmployeeType())
                .councilNumber(user.getCouncilNumber())
                .councilState(user.getCouncilState())
                .specialty(user.getSpecialty())
                .role(Role.builder().name(user.getRoleEntity().getName()).build())
                .build();
    }
}
