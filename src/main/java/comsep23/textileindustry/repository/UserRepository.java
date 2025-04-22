package comsep23.textileindustry.repository;

import comsep23.textileindustry.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
