package dev.tveir.backendmessage.user;

import dev.tveir.backendmessage.model.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInterface extends JpaRepository<Users, Integer> {

    List<Users> findAllProjectedBy();
}
