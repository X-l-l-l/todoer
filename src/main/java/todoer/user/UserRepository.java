package todoer.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implements all the methods through which fields from the users table are gotten
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param email email by which the user is searched
     * @return the user that was found based on the email
     */
    Optional<User> findUserByEmail(String email);

    /**
     * @param username username by which the user is searched
     * @return the user that was found based on the username
     */
    Optional<User> findUserByUsername(String username);

    User getUserById(Long userId);
}
