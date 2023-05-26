package todoer.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> getGroupById(Long id);

    @Query(value = "SELECT u.groups from User u where u.id=?1")
    List<Group> findGroupsByUsersId(Long userId);
}
