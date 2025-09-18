package com.xworkz.kabhishek_xcare_hospital.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "timing_slot_table")
public class TimingSlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;
}
