package todoer.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todoer.item.Item;

import java.util.List;

/**
 * Implements all the methods through which fields from the notifications table are gotten
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getNotificationsByUserId(Long userId);
}
