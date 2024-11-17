package com.api.UserPhoto.DTO;

import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private Long document;
    private String name ;
    private String lastname;
    private String photo;
    private LocalDate date_birth;
    private String blood_type;
    private String email;
    private String phone;
    private String address;
    private Boolean state;
    private Date createdAt;
    private Date updatedAt;
}