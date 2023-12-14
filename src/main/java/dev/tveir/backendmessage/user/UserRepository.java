package dev.tveir.backendmessage.user;

import dev.tveir.backendmessage.model.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<UserResponse> findAllProjectedBy();
    boolean existsByUsername(String username);

    List<User> findAllByStatus(Status status);
}
