package com.xworkz.kabhishek_xcare_hospital.entity;

import javax.persistence.*;

@Entity
@Table(name = "image_table")
public class ImageEntity {

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




