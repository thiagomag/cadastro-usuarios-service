package br.com.postechfiap.cadastrousuarioservice.application.usecases;

import br.com.postechfiap.cadastrousuarioservice.application.gatways.RoleGateway;
import br.com.postechfiap.cadastrousuarioservice.application.gatways.UserGateway;
import br.com.postechfiap.cadastrousuarioservice.application.interfaces.CreateUserUseCase;
import br.com.postechfiap.cadastrousuarioservice.domain.entity.User;
import br.com.postechfiap.cadastrousuarioservice.domain.enums.EmployeeTypeEnum;
import br.com.postechfiap.cadastrousuarioservice.domain.enums.RoleNameEnum;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.UserResponse;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.gateways.adapters.UserEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway userGateway;
    private final RoleGateway roleGateway;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public UserResponse execute(User createUser) {
        final var roleNameEnum = getRoleType(createUser.getEmployeeTypeEnum());
        final var role = roleGateway.findByRoleName(roleNameEnum.name())
                .orElseThrow(() -> new EntityNotFoundException("Role not found for employee type: " + roleNameEnum.name()));
        final var userEntity = userEntityAdapter.toUserEntity(createUser, role);
        return userEntityAdapter.toUserResponse(userGateway.save(userEntity));
    }

    private RoleNameEnum getRoleType(EmployeeTypeEnum employeeTypeEnum) {
        return switch (employeeTypeEnum) {
            case EmployeeTypeEnum.ADMINISTRATIVE -> RoleNameEnum.ROLE_ADMINISTRATOR;
            case EmployeeTypeEnum.MEDICAL -> RoleNameEnum.ROLE_MEDIC;
            case EmployeeTypeEnum.NURSE -> RoleNameEnum.ROLE_NURSE;
        };
    }
}
