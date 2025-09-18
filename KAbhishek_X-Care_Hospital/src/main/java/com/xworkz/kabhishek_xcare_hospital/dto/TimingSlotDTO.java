package com.xworkz.kabhishek_xcare_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimingSlotDTO {
    private String startTime;
    private String endTime;
}
