package com.xworkz.kabhishek_xcare_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimingSlotDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String specialty;
    private String startTime;
    private String endTime;
}
