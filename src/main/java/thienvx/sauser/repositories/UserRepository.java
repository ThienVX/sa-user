package thienvx.sauser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thienvx.sauser.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
