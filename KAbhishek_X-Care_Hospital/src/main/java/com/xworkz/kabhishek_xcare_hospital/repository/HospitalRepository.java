package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;

public interface HospitalRepository {
    AdminEntity checkAdmin(String gmail);
}
