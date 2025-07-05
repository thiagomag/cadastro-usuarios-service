package br.com.postechfiap.cadastrousuarioservice.application.usecases;

import br.com.postechfiap.cadastrousuarioservice.application.gatways.UserGateway;
import br.com.postechfiap.cadastrousuarioservice.application.interfaces.DeleteUserUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserGateway userGateway;

    @Override
    public Void execute(Long userId) {
        final var userEntity = userGateway.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        userGateway.delete(userEntity);
        return null;
    }
}
