package com.picpay.domain.transaction;

import com.picpay.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name= "transactions")
@Table(name= "transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;

    @JoinColumn(name= "sender_id")
    @ManyToMany
    private User sender;

    @JoinColumn(name= "receiver_id")
    @ManyToOne
    private User receiver;

    private LocalDateTime timestamp;
}
