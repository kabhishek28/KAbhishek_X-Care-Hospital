package com.xworkz.kabhishek_xcare_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String doctorName;
    private String doctorEmail;
    private long doctorPhoneNo;
    private String license_number;
    private String specialty;
    private String doctorGender;
    private String qualification;
    private int experience;


}
