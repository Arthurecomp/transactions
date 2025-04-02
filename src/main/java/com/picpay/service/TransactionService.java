package com.picpay.service;

import com.picpay.domain.transaction.Transaction;
import com.picpay.domain.user.User;
import com.picpay.dtos.TransactionDTO;
import com.picpay.repositories.TransactionRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validadeTransaction(sender,transaction.value());

//        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
//        if(!isAuthorized){
//            throw new Exception("Transação não autorizada");
//        }

        Transaction newTransaction = new Transaction();

        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setAmount(transaction.value());
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender,"Transação realizada com sucesso");
        this.notificationService.sendNotification(receiver, "transação recebida com sucesso");


        return newTransaction;
    }

//    public boolean authorizeTransaction(User sender, BigDecimal value){
//        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
//
//        if (authorizationResponse.getStatusCode() == HttpStatus.OK &&
//                authorizationResponse.getBody() != null &&
//                ((Map<?, ?>)authorizationResponse.getBody().get("data")).get("authorization") != null) {
//
//            boolean isAuthorized = (boolean) ((Map<?, ?>) authorizationResponse.getBody().get("data")).get("authorization");
//            return isAuthorized;
//        } else {
//            return false;
//        }
//    }
}
