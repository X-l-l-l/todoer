package todoer.serviceInterfaces;

import todoer.notification.Notification;

import java.util.List;

public interface NotificationServiceInterface {
    void createNotification(Notification notification);

    void deleteNotification(Long notificationId);

    List<Notification> getUserNotifications(Long userId);
}
