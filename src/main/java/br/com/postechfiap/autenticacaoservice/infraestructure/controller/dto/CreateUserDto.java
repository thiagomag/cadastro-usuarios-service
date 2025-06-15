package br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto;

import br.com.postechfiap.autenticacaoservice.domain.enums.EmployeeTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDto(

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail deve ser válido.")
        String email,
        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,
        @NotBlank(message = "O nome completo é obrigatório.")
        String fullName,
        @NotNull(message = "O tipo de funcionário é obrigatório.")
        EmployeeTypeEnum employeeTypeEnum,
        String councilNumber,
        String councilState,
        String specialty

) {
}
