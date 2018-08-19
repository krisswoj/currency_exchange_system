package pl.krzysiek.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.Role;

@Repository
public interface IRoleRepository extends CrudRepository <Role, Integer> {
    Role findByRole(String role);
}
