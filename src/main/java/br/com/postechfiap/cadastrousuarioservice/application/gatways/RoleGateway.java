package br.com.postechfiap.cadastrousuarioservice.application.gatways;

import br.com.postechfiap.cadastrousuarioservice.infraestructure.persistence.entities.RoleEntity;

import java.util.Optional;

public interface RoleGateway extends BaseGateway<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(String name);
}
