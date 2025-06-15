package br.com.postechfiap.autenticacaoservice.application.usecases;

import br.com.postechfiap.autenticacaoservice.application.gatways.UserGateway;
import br.com.postechfiap.autenticacaoservice.application.interfaces.GetUserUseCase;
import br.com.postechfiap.autenticacaoservice.domain.enums.EmployeeTypeEnum;
import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.UserResponse;
import br.com.postechfiap.autenticacaoservice.infraestructure.gateways.adapters.UserEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {

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
