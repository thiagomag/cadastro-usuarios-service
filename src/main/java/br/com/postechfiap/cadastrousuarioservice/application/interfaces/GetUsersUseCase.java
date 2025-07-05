package br.com.postechfiap.cadastrousuarioservice.application.interfaces;

import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.UserResponse;

import java.util.List;

public interface GetUsersUseCase extends UseCase<String, List<UserResponse>> {
}
