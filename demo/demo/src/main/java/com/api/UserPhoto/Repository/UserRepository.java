package com.api.UserPhoto.Repository;

import com.api.UserPhoto.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByDocument(Long document);
}