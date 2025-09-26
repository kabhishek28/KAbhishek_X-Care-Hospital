package com.xworkz.kabhishek_xcare_hospital.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "timing_slot_table")
@NamedQuery(name = "getTimeSlotBySpecialty",query = "select e from TimingSlotEntity e where e.specialty=:specialtyBy ")
//@NamedQuery(name = "updateSlotAssign",query = "update TimingSlotEntity t set t.slotBooked = 1 where t.startTime=:startTimeBy and t.endTime=:endTimeBy")
@NamedQuery(name = "checkTimeSlotBySpecialty",query = "select e from TimingSlotEntity e where e.specialty=:specialtyBy ")

public class TimingSlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timing_slot_id")
    private int id;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @OneToMany(mappedBy = "timingSlotEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    List<DoctorSlotAssignmentEntity> assignmentEntities;




}
