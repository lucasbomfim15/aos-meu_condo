package com.aos.meu_condo.modules.user.repository;


import com.aos.meu_condo.modules.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByEmailOrUsername(String email, String username);
}
