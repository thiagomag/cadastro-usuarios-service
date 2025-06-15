package br.com.postechfiap.autenticacaoservice.application.gatways;

import br.com.postechfiap.autenticacaoservice.infraestructure.persistence.entities.RoleEntity;

import java.util.Optional;

public interface RoleGateway extends BaseGateway<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(String name);
}
