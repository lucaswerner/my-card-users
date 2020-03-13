package com.mycard.users.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public
@Data
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Boolean stActive = Boolean.TRUE;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

}
