package br.com.postechfiap.autenticacaoservice.infraestructure.controller.adapters;

import br.com.postechfiap.autenticacaoservice.domain.entity.User;
import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.CreateUserDto;
import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.UpdateUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {

    public User toUser(CreateUserDto createUserDto) {
       return User.builder()
               .fullName(createUserDto.fullName())
               .email(createUserDto.email())
               .password(createUserDto.password())
               .employeeTypeEnum(createUserDto.employeeTypeEnum())
               .specialty(createUserDto.specialty())
               .councilNumber(createUserDto.councilNumber())
               .councilState(createUserDto.councilState())
               .build();
    }

    public User updateUser(UpdateUserDto updateUserDto) {
        return User.builder()
                .fullName(updateUserDto.getFullName())
                .email(updateUserDto.getEmail())
                .password(updateUserDto.getPassword())
                .employeeTypeEnum(updateUserDto.getEmployeeTypeEnum())
                .specialty(updateUserDto.getSpecialty())
                .councilNumber(updateUserDto.getCouncilNumber())
                .councilState(updateUserDto.getCouncilState())
                .emailChangeAuthorized(updateUserDto.getEmailChangeAuthorized())
                .passwordChangeAuthorized(updateUserDto.getPasswordChangeAuthorized())
                .build();
    }
}
