package com.xworkz.kabhishek_xcare_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorWithSlotsDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String optionDoctorName;
    private String timings;
    private String doctorEmail;
    private String specialty;
}
