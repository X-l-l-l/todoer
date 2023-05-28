package todoer.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import todoer.item.ItemRepository;
import todoer.serviceInterfaces.NotificationServiceInterface;

import java.util.List;

@Service
public class NotificationService implements NotificationServiceInterface {

    final private NotificationRepository notificationRepository;
    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    @Override
    public void createNotification(Notification notification) {
        this.notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new IllegalStateException(
                    "item with id " + notificationId + " does not exist");
        }
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.getNotificationsByUserId(userId);
    }
}
