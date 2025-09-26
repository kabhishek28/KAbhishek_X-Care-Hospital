package com.xworkz.kabhishek_xcare_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSlotAssignmentDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int doctorID;
    private int slotID;
    private String optionDoctorName;
    private String timings;
    private String doctorEmail;
    private String specialty;
}
