package br.com.postechfiap.cadastrousuarioservice.infraestructure.gateways.adapters;

import br.com.postechfiap.cadastrousuarioservice.domain.entity.Role;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.persistence.entities.RoleEntity;
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
