package br.com.postechfiap.autenticacaoservice.infraestructure.controller;

import br.com.postechfiap.autenticacaoservice.application.interfaces.*;
import br.com.postechfiap.autenticacaoservice.infraestructure.controller.adapters.UserAdapter;
import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserAdapter userAdapter;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.ok(authenticateUserUseCase.execute(loginUserDto));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserDto createUserDto) {
        final var user = userAdapter.toUser(createUserDto);
        return new ResponseEntity<>(createUserUseCase.execute(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(@RequestParam(required = false) String employeeType) {
        return ResponseEntity.ok(getUserUseCase.execute(employeeType));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UpdateUserDto updateUserDto) {
        final var user = userAdapter.updateUser(updateUserDto);
        user.setId(userId);
        return ResponseEntity.ok(updateUserUseCase.execute(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        deleteUserUseCase.execute(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return new ResponseEntity<>("Cliente autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
    }

}