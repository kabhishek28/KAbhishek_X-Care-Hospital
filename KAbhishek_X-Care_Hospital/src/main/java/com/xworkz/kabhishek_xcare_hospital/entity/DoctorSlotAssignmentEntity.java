package com.xworkz.kabhishek_xcare_hospital.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "doctor_slots_assignment_table")
@NamedQuery(name = "TimingSlotEntityCheckDoctorSlotExists", query = "SELECT COUNT(t) FROM DoctorSlotAssignmentEntity t WHERE t.doctorEmail =:doctorEmailBy AND t.timings =:slotTimeBy"
)
public class DoctorSlotAssignmentEntity {

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

    @ManyToOne
    @JoinColumn(name="doc_id",referencedColumnName = "doctor_id")
    private DoctorEntity doctorEntity;

    @ManyToOne
    @JoinColumn(name = "slots_id",referencedColumnName = "timing_slot_id")
    private TimingSlotEntity timingSlotEntity;
}
