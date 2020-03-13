package com.mycard.cards.dto;

import lombok.Data;

import java.time.LocalDateTime;

public @Data
class PostUserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private Boolean stActive = Boolean.TRUE;
    private LocalDateTime timestamp = LocalDateTime.now();
}
