package br.com.postechfiap.cadastrousuarioservice.application.usecases;

import br.com.postechfiap.cadastrousuarioservice.application.gatways.UserGateway;
import br.com.postechfiap.cadastrousuarioservice.application.interfaces.GetUsersUseCase;
import br.com.postechfiap.cadastrousuarioservice.domain.enums.EmployeeTypeEnum;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.UserResponse;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.gateways.adapters.UserEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {

    private final UserGateway userRepository;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public List<UserResponse> execute(String employeeType) {
        if (employeeType == null || employeeType.isEmpty()) {
            return userRepository.findAll()
                    .stream()
                    .map(userEntityAdapter::toUserResponse)
                    .toList();
        }
        return userRepository.findByEmployeeType(EmployeeTypeEnum.valueOf(employeeType))
                .stream()
                .map(userEntityAdapter::toUserResponse)
                .toList();
    }
}
