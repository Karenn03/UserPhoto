package com.api.UserPhoto.Service;

import jakarta.persistence.*;
import com.api.UserPhoto.Entity.UserEntity;
import com.api.UserPhoto.Repository.UserRepository;
import com.api.UserPhoto.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IDAO<UserEntity, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getById(Long id) {
        Optional<UserEntity> optionalPerson = userRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        return optionalPerson.get();
    }

    @Override
    public void update(UserEntity entity) {
        this.userRepository.save(entity);
    }

    @Override
    public UserEntity save(UserEntity entity) {
        return this.userRepository.save(entity);
    }

    @Override
    public void delete(UserEntity entity) {
        this.userRepository.delete(entity);
    }

    @Override
    public void create(UserEntity entity) {
        this.userRepository.save(entity);
    }

    public UserEntity findByDocument(Long document) {
        return userRepository.findByDocument(document);
    }
}