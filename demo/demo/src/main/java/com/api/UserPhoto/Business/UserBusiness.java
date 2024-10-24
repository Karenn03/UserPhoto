package com.api.UserPhoto.Business;

import com.api.UserPhoto.DTO.UserDTO;
import com.api.UserPhoto.Entity.UserEntity;
import com.api.UserPhoto.Service.UserService;
import com.api.UserPhoto.Utilities.CustomException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserBusiness {

    @Autowired
    private UserService userService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<UserDTO> findAll(){
        try {
            List<UserEntity> personasList = userService.findAll();
            if (personasList.isEmpty()){
                return new ArrayList<>();
            }
            List<UserDTO> personasDtoList = new ArrayList<>();
            personasList.forEach(PersonaEntity -> personasDtoList.add(modelMapper.map(PersonaEntity,UserDTO.class)));
            return personasDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las personas.");
        }
    }

    public UserDTO getById(Long id){
        try {
            UserEntity personaEntity = userService.getById(id);
            if (personaEntity == null){
                throw new CustomException("Persona con id " + id + " no encontrada.");
            }
            return modelMapper.map(personaEntity, UserDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener la persona por id.");
        }
    }

    public void update(Long id, UserDTO userDto) {
        try {
            UserEntity existingUser = userService.getById(id);
            if (existingUser == null) {
                throw new CustomException("Persona con id " + id + " no se encuentra.");
            }
            existingUser.setDocument(userDto.getDocument());
            existingUser.setName(userDto.getName());
            existingUser.setLastname(userDto.getLastname());
            existingUser.setPhoto(userDto.getPhoto());
            existingUser.setDate_birth(userDto.getDate_birth());
            existingUser.setBlood_type(userDto.getBlood_type());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setPhone(userDto.getPhone());
            existingUser.setAddress(userDto.getAddress());
            existingUser.setState(userDto.getState());
            existingUser.setCreatedAt(userDto.getCreatedAt());
            existingUser.setUpdatedAt(userDto.getUpdatedAt());
            userService.save(existingUser);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la persona.");
        }
    }

    public void create(UserDTO userDto){
        try {
            Long Document = userDto.getDocument();
            UserEntity existingUser = userService.findByDocument(Document);
            if (existingUser != null) {
                throw new CustomException("La persona con el documento " + Document + " ya existe.");
            }
            UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
            userService.save(userEntity);
        } catch (Exception e){
            throw new CustomException("Error creando la persona.");
        }
    }

    public void delete(Long idPersonas) {
        try {
            UserEntity personaEntity = userService.getById(idPersonas);
            if (personaEntity == null) {
                throw new CustomException("Persona con id " + idPersonas + " no encontrada.");
            }
            userService.delete(personaEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando la persona: " + e.getMessage());
        }
    }

    public void savePhoto(Long id, MultipartFile photoFile) throws IOException {
        UserEntity user = userService.getById(id);
        byte[] photoBytes = photoFile.getBytes();
        user.setPhoto(photoBytes);
        userService.save(user);
    }

    public byte[] getPhoto(Long id) {
        UserEntity user = userService.getById(id);
        if (user.getPhoto() == null) {
            throw new CustomException("La persona con id " + id + " no tiene una foto asociada.");
        }
        return user.getPhoto();
    }

}