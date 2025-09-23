package com.xworkz.kabhishek_xcare_hospital.entity;

import com.xworkz.kabhishek_xcare_hospital.constants.Specialty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "doctor_table")
@NamedQuery(name = "findDoctorListBySpecialty", query = "select e from DoctorEntity e where e.specialty=:specialtyBy ")
@NamedQuery(name = "SetDoctorSlots",query = "update DoctorEntity d SET d.slotAssign = 1 , d.slotTiming=:slotTimingBy where d.doctorEmail=:doctorEmailBy And d.specialty=:specialtyBy")
@NamedQuery(name = "checkDoctorListBySpecialty",query = "select e from DoctorEntity e where e.specialty=:specialtyBy" )
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "doctor_Name")
    private String doctorName;

    @Column(name = "doctor_Email")
    private String doctorEmail;

    @Column(name = "doctor_PhoneNo")
    private long doctorPhoneNo;

    @Column(name = "license_number")
    private String license_number;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "doctor_Gender")
    private String doctorGender;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "experience")
    private int experience;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "slot_timing")
    private String slotTiming;

    @Column(name = "slot_assign")
    private int slotAssign;
}
