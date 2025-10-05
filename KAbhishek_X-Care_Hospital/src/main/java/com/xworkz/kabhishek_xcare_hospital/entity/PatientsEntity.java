package com.xworkz.kabhishek_xcare_hospital.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patients_table")
public class PatientsEntity extends AuditEntity {

    @Id
    @Column(name = "patients_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "patient_age")
    private int age;

    @Column(name = "patient_gender")
    private String gender;

    @Column(name = "patient_contact_no")
    private long contact;

    @Column(name = "patient_disease")
    private String disease;

    @Column(name = "patient_blood_group")
    private String bloodGroup;

    @Column(name = "patient_specialty")
    private String specialty;

    @Column(name = "patient_appointment_time")
    private String appointmentTime;

    @Column(name = "patient_address")
    private String address;

    @Column(name = "doctor_fees")
    private int fees;

    @ManyToOne
    @JoinColumn(name = "doc_id",referencedColumnName = "doctor_id")
    DoctorEntity doctorEntity;


}
