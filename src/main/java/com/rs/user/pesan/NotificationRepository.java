package com.rs.user.pesan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationMessageModel, Integer> {
}
