package br.com.postechfiap.autenticacaoservice.infraestructure.gateways.adapters;

import br.com.postechfiap.autenticacaoservice.domain.entity.Role;
import br.com.postechfiap.autenticacaoservice.infraestructure.persistence.entities.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityAdapter {

    public RoleEntity toRoleEntity(final Role role) {
        if (role == null) {
            return null;
        }
        return RoleEntity.builder()
                .name(role.getName())
                .build();
    }
}
