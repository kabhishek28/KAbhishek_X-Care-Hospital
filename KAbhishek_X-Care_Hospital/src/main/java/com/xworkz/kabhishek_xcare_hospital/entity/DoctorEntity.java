package com.xworkz.kabhishek_xcare_hospital.entity;

import com.xworkz.kabhishek_xcare_hospital.constants.Specialty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "doctor_table")

public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "doctor_Name")
    private String doctorName;

    @Column(name = "doctor_Email")
    private String doctorEmail;

    @Column(name = "doctor_PhoneNo")
    private long doctorPhoneNo;

    @Column(name = "license_number")
    private String license_number;

    @Column(name = "specialty")
    private Specialty specialty;

    @Column(name = "doctor_Gender")
    private String doctorGender;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "experience")
    private int experience;

    @Column(name = "image_path")
    private String imagePath;
}
