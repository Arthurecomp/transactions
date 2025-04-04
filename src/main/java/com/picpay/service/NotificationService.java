package com.picpay.service;

import com.picpay.domain.user.User;
import com.picpay.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.DocFlavor;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification (User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

//        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);
//        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
//            System.out.println("erro ao enviar notificação");
//            throw new Exception("Serviço de notificação fora do ar");
//        }
        System.out.println("notificação enviada");
    }
}
