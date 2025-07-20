package br.com.postechfiap.cadastrousuarioservice.infraestructure.controller;

import br.com.postechfiap.cadastrousuarioservice.application.interfaces.*;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.adapters.UserAdapter;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Usuários",description = "Api de gerenciamento de usuários")
public class UserController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserAdapter userAdapter;

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Endpoint para autenticar um usuário e retornar um token JWT")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.ok(authenticateUserUseCase.execute(loginUserDto));
    }

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Endpoint para criar um novo usuário")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserDto createUserDto) {
        final var user = userAdapter.toUser(createUserDto);
        return new ResponseEntity<>(createUserUseCase.execute(user), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @Operation(summary = "Listar usuários", description = "Endpoint para listar todos os usuários ou filtrar por tipo de funcionário")
    public ResponseEntity<List<UserResponse>> getUsers(@RequestParam(required = false) String employeeType) {
        return ResponseEntity.ok(getUsersUseCase.execute(employeeType));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_MEDIC', 'ROLE_NURSE')")
    @Operation(summary = "Listar usuários por id", description = "Endpoint para listar usuáriospor id")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(getUserUseCase.execute(userId));
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_MEDIC', 'ROLE_NURSE')")
    @Operation(summary = "Atualizar usuário", description = "Endpoint para atualizar um usuário existente")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UpdateUserDto updateUserDto) {
        final var user = userAdapter.updateUser(updateUserDto);
        user.setId(userId);
        return ResponseEntity.ok(updateUserUseCase.execute(user));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @Operation(summary = "Deletar usuário", description = "Endpoint para deletar um usuário existente")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        deleteUserUseCase.execute(userId);
        return ResponseEntity.noContent().build();
    }
}