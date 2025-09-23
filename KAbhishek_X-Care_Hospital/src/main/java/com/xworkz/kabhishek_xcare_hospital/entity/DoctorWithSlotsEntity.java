package com.xworkz.kabhishek_xcare_hospital.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "doctor_slots_table")
@NamedQuery(name = "TimingSlotEntityCheckDoctorSlotExists", query = "SELECT COUNT(t) FROM DoctorWithSlotsEntity t WHERE t.doctorEmail =:doctorEmailBy AND t.timings =:slotTimeBy"
)
public class DoctorWithSlotsEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "doctor_name")
    private String optionDoctorName;

    @Column(name = "timings")
    private String timings;

    @Column(name = "doctor_email")
    private String doctorEmail;

    @Column(name = "specialty")
    private String specialty;
}
