package s.praveen.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s.praveen.cms.model.dto.User;

/**
 * The interface User repository.
 */
@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Long> {
}
