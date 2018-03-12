package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.NotificationChannel;

@Repository
public interface NotificationChannelRepository extends JpaRepository<NotificationChannel, Byte>{
	List<NotificationChannel> findByIsActive(Boolean isActive);
	NotificationChannel findByCode(String code);
}
