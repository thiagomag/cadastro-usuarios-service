package br.com.postechfiap.autenticacaoservice.application.usecases;

import br.com.postechfiap.autenticacaoservice.application.gatways.RoleGateway;
import br.com.postechfiap.autenticacaoservice.application.gatways.UserGateway;
import br.com.postechfiap.autenticacaoservice.application.interfaces.UpdateUserUseCase;
import br.com.postechfiap.autenticacaoservice.domain.entity.User;
import br.com.postechfiap.autenticacaoservice.domain.enums.EmployeeTypeEnum;
import br.com.postechfiap.autenticacaoservice.domain.enums.RoleNameEnum;
import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.UserResponse;
import br.com.postechfiap.autenticacaoservice.infraestructure.gateways.adapters.UserEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserGateway userGateway;
    private final RoleGateway roleGateway;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public UserResponse execute(User user) {
        final var userId = user.getId();
        final var userEntityFromDb = userGateway.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        final var roleNameEnum = user.getEmployeeTypeEnum() != null ? getRoleType(user.getEmployeeTypeEnum()) : userEntityFromDb.getRoleEntity().getName();
        final var role = roleGateway.findByRoleName(roleNameEnum.name())
                .orElseThrow(() -> new EntityNotFoundException("Role not found for employee type: " + roleNameEnum.name()));
        final var userEntityUpdated = userEntityAdapter.updateUserEntity(userEntityFromDb, user, role);
        return userEntityAdapter.toUserResponse(userGateway.update(userEntityUpdated));
    }

    private RoleNameEnum getRoleType(EmployeeTypeEnum employeeTypeEnum) {
        return switch (employeeTypeEnum) {
            case EmployeeTypeEnum.ADMINISTRATIVE -> RoleNameEnum.ROLE_ADMINISTRATOR;
            case EmployeeTypeEnum.MEDICAL -> RoleNameEnum.ROLE_MEDIC;
            case EmployeeTypeEnum.NURSE -> RoleNameEnum.ROLE_NURSE;
        };
    }
}
