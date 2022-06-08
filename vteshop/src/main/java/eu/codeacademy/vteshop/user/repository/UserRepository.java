package eu.codeacademy.vteshop.user.repository;

import eu.codeacademy.vteshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u JOIN FETCH u.authorities WHERE u.email = :email")
    Optional<User> findUserByEmailWithAuthorities(String email);
}
