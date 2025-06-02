package br.com.postechfiap.autenticacaoservice.repository;

import br.com.postechfiap.autenticacaoservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r.* from roles r "+
            "where r.name = :name", nativeQuery = true)
    Optional<Role> findByRoleName(String name);
}
