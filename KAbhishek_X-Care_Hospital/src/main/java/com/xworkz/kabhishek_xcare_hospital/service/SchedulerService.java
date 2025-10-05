package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;
import com.xworkz.kabhishek_xcare_hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulerService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Scheduled(fixedRate = 120000)
    public void checkOTPExpiry(){
        List<AdminEntity> adminEntityList = hospitalRepository.getAllAdmin();
        LocalDateTime localDateTime = LocalDateTime.now();
        for(AdminEntity adminEntity : adminEntityList){
            if(adminEntity.getLocalDateTime() != null && localDateTime.isAfter(adminEntity.getLocalDateTime())){
                    adminEntity.setOtp(null);
                    adminEntity.setLocalDateTime(null);
                    hospitalRepository.upDateAdminEntity(adminEntity);
            }
        }
    }
}
