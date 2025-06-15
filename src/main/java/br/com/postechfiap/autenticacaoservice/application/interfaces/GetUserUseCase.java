package br.com.postechfiap.autenticacaoservice.application.interfaces;

import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.UserResponse;

import java.util.List;

public interface GetUserUseCase extends UseCase<String, List<UserResponse>> {
}
