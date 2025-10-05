package com.xworkz.kabhishek_xcare_hospital.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image_table")
public class ImageEntity extends AuditEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "original_image_name")
    private String originalImageName;

    @Column(name = "changed_name")
    private String changedName;

    @Column(name = "image_size")
    private long ImageSize;

    @Column(name = "image_path")
    private  String imagePath;

    @OneToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "doctor_id")
    DoctorEntity doctorEntity;

}




