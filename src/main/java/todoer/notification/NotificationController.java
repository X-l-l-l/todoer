package todoer.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todoer.item.Item;
import todoer.serviceInterfaces.ItemServiceInterface;
import todoer.serviceInterfaces.NotificationServiceInterface;

import java.util.List;

@RestController
@RequestMapping("notifications")
public class NotificationController {

    private final NotificationServiceInterface notificationService;

    @Autowired
    public NotificationController(NotificationServiceInterface notificationService){
        this.notificationService = notificationService;
    }
    @CrossOrigin
    @PostMapping
    public void addNotification(@RequestBody Notification notification)
    {
        this.notificationService.createNotification(notification);
    }

    @CrossOrigin
    @DeleteMapping(path = "{notificationId}")
    public void deleteNotification(@PathVariable("notificationId") Long notificationId){
        notificationService.deleteNotification(notificationId);
    }

    @CrossOrigin
    @GetMapping(path = "{userId}")
    public List<Notification> getUserNotifications(@PathVariable("userId") Long userId){
        return notificationService.getUserNotifications(userId);
    }
}
