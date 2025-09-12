package com.xworkz.kabhishek_xcare_hospital.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "admin_Table")
@NamedQuery(name = "findAdminByGmail" , query = "from AdminEntity e where e.email=:em")
@NamedQuery(name = "countOfEmail",query = "select count(e.email) from AdminEntity e where e.email=:emailBy")
@NamedQuery(name = "updateOTPByGmail" , query = "UPDATE AdminEntity e SET e.otp=:otpby , e.localDateTime=:dateTime where e.email=:emailby")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;

    @Column(name = "admin_gmail")
    private String email;

    @Column(name = "admin_otp")
    private String otp;

    @Column(name = "admin_login_Date_Time")
    private LocalDateTime localDateTime;
}
