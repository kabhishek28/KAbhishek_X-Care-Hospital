package com.xworkz.kabhishek_xcare_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientsDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String patientName;
    private int age;
    private String gender;
    private long contact;
    private String disease;
    private String bloodGroup;
    private String specialty;
    private int doctor;
    private String appointmentTime;
    private String address;
    private int fees;

}
