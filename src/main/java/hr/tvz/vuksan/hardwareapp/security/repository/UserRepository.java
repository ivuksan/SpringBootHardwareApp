package hr.tvz.vuksan.hardwareapp.security.repository;

import hr.tvz.vuksan.hardwareapp.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
