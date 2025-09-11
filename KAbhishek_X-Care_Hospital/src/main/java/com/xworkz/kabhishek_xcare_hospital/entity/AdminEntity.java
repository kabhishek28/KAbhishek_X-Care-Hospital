package com.xworkz.kabhishek_xcare_hospital.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "admin_Table")
@NamedQuery(name = "findAdminByGmail" , query = "from AdminEntity e where e.email=:em")
@NamedQuery(name = "countOfEmail",query = "select count(e.email) from AdminEntity e where e.email=:emailBy")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;

    @Column(name = "admin_gmail")
    private String email;

    @Column(name = "admin_otp")
    private Integer otp;

    @Column(name = "admin_login_Date_Time")
    private LocalDateTime localDateTime;
}
