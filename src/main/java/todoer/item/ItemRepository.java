package todoer.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implements all the methods through which fields from the item table are gotten
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
