package com.api.UserPhoto.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
// @EqualsAndHashCode(exclude ={"user","student","guest","equipmentList","entranceList","exitList","document_type","attendanceList"})
// @ToString(exclude ={"user","student","guest","equipmentList","entranceList","exitList","document_type","attendanceList"})
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name ="people")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true, nullable = false)
    private Long document;

    @Column (nullable = false , length = 80)
    private String name ;

    @Column (nullable = false , length = 80)
    private String lastname;

    @Lob
    @Column (unique = true)
    private byte[] photo;

    @Column (nullable = false)
    private LocalDate date_birth;

    @Column (nullable = false, length = 10)
    private String blood_type;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false, length = 20)
    private String phone;

    @Column (nullable = false, length = 100)
    private String address;

    @Column (nullable = false, columnDefinition = "boolean default true")
    private Boolean state;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updatedAt;

    /*
    Relations

    @OneToOne(mappedBy = "person")
    private User user;

    @OneToOne(mappedBy = "person")
    private Student student;

    @OneToOne(mappedBy = "person")
    private Guest guest;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Equipment> equipmentList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Entrance> entranceList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Exit> exitList;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn (name = "fk_id_document_type")
    private DocumentType document_type;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Attendance> attendanceList;

    */
}