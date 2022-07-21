package eu.codeacademy.vteshop.security.jpa.repository;

import eu.codeacademy.vteshop.jpa.user.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
