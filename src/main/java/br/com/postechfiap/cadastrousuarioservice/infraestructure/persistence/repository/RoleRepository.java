package br.com.postechfiap.cadastrousuarioservice.infraestructure.persistence.repository;

import br.com.postechfiap.cadastrousuarioservice.infraestructure.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "select r.* from roles r "+
            "where r.name = :name", nativeQuery = true)
    Optional<RoleEntity> findByRoleName(String name);
}
