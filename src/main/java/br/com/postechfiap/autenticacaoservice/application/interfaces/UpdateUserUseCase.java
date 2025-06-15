package br.com.postechfiap.autenticacaoservice.application.interfaces;

import br.com.postechfiap.autenticacaoservice.domain.entity.User;
import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.UserResponse;

public interface UpdateUserUseCase extends UseCase<User, UserResponse> {
}
