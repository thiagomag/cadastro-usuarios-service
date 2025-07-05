package br.com.postechfiap.cadastrousuarioservice.application.usecases;

import br.com.postechfiap.cadastrousuarioservice.application.interfaces.GetUserUseCase;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.UserResponse;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.gateways.UserRepositoryGateway;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.gateways.adapters.UserEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public UserResponse execute(Long userId) {
        final var user = userRepositoryGateway.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return userEntityAdapter.toUserResponse(user);
    }
}
