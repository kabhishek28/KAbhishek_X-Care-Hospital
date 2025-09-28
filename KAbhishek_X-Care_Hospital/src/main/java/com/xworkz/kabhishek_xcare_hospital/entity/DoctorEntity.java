package com.xworkz.kabhishek_xcare_hospital.entity;

import com.xworkz.kabhishek_xcare_hospital.constants.Specialty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "doctor_table")
@NamedQuery(name = "findDoctorListBySpecialty", query = "select e from DoctorEntity e where e.specialty=:specialtyBy ")
@NamedQuery(name = "getAllDoctorsList",query = "select e from DoctorEntity e")
//@NamedQuery(name = "SetDoctorSlots",query = "update DoctorEntity d   d.slotTiming=:slotTimingBy where d.doctorEmail=:doctorEmailBy And d.specialty=:specialtyBy")
@NamedQuery(name = "getDoctorEntityByEmail",query = "select e from DoctorEntity e where e.doctorEmail=:emailBy")
@NamedQuery(name = "checkDoctorListBySpecialty",query = "select e from DoctorEntity e where e.specialty=:specialtyBy" )
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
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


    @OneToMany(mappedBy = "doctorEntity",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DoctorSlotAssignmentEntity> assignmentEntities;


    @OneToOne(mappedBy = "doctorEntity",cascade = CascadeType.ALL)
    @ToString.Exclude
    private ImageEntity imageEntity;


}
