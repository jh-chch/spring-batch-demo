package com.example.batch.domain;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String name;
    private String email;
    private Boolean isActive;
    private LocalDateTime lastNotifiedAt;
    private LocalDateTime createdAt;
    
    public User() {
    }

    public User(Long id, String name, String email, Boolean isActive, LocalDateTime lastNotifiedAt,
            LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
        this.lastNotifiedAt = lastNotifiedAt;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public LocalDateTime getLastNotifiedAt() {
        return lastNotifiedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
