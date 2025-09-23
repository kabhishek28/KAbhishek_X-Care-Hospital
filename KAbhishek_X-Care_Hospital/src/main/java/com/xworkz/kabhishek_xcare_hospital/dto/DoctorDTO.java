package com.xworkz.kabhishek_xcare_hospital.dto;

import com.xworkz.kabhishek_xcare_hospital.constants.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String doctorName;
    private String doctorEmail;
    private long doctorPhoneNo;
    private String license_number;
    private String specialty;
    private String doctorGender;
    private String qualification;
    private int experience;
    private String imagePath;
}
