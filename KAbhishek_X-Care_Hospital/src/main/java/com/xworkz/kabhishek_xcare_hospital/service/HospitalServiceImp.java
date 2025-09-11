package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;
import com.xworkz.kabhishek_xcare_hospital.repository.HospitalRepository;
import com.xworkz.kabhishek_xcare_hospital.repository.HospitalRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImp implements HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public String checkAdmin(String gmail) {
        AdminEntity adminEntity = hospitalRepository.checkAdmin(gmail);

        if(adminEntity.getEmail() != null ){
            return "gmail exist";
        }
        return "gmail not exist";
    }
}
