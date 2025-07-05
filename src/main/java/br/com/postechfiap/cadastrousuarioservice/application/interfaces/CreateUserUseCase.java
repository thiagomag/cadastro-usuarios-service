package br.com.postechfiap.cadastrousuarioservice.application.interfaces;

import br.com.postechfiap.cadastrousuarioservice.domain.entity.User;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.UserResponse;

public interface CreateUserUseCase extends UseCase<User, UserResponse>{
}
