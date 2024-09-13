package com.rs.user.pesan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/message")
public class NotificationController {

    @Autowired
    private NotificationRepository repository;

    @PostMapping("/sendMessageToAllUsers/{message}")
    public void sendMessageToAllUsers(
            @PathVariable("message") String message,
            @PathVariable("title") String title)
    {
        PushNotificationOptions.sendMessageToAllUsers(message, title);
    }

    @PostMapping("/sendMessageToUser/{userId}/{message}")
    public void sendMessageToUser(@PathVariable("userId") String userId,
                                  @PathVariable("message") String message,
                                  @PathVariable("title") String title)
    {
        PushNotificationOptions.sendMessageToUser(message, userId, title);
    }

    @PostMapping("/saveUserId/{userId}")
    public void saveUserId(@PathVariable("userId") String userId)
    {
        NotificationMessageModel notification = new NotificationMessageModel();
        notification.setIdOneSignal(userId);
        notification.setUserName("User: " + userId);
        repository.save(notification);
    }

    @GetMapping("/getUsers")
    public List<Object> getUsers()
    {
        List<Object> listValues = new ArrayList<>();
        for (NotificationMessageModel notification : repository.findAll()){
            Map<String, Object> mapValues = new HashMap<>();
            mapValues.put("userName", notification.getUserName());
            mapValues.put("idOneSignal", notification.getIdOneSignal());
            listValues.add(mapValues);
        }

        return listValues;
    }
}
