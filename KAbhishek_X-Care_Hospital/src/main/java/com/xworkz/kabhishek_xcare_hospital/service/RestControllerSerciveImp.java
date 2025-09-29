package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;
import com.xworkz.kabhishek_xcare_hospital.repository.RestControllerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestControllerSerciveImp implements RestControllerSercive {

    @Autowired
    RestControllerRepository restControllerRepository;

    @Override
    public int countEmail(String email) {
        return restControllerRepository.countEmail(email);
    }

    @Override
    public List<DoctorDTO> checkDoctorList(String specialty) {

        List<DoctorEntity> list =  restControllerRepository.checkDoctorList(specialty);
        List<DoctorDTO> doctors = new ArrayList<>();

        for (DoctorEntity doctorEntity : list){
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctorEntity,doctorDTO);
            doctors.add(doctorDTO);
        }

        return doctors;
    }

    @Override
    public List<TimingSlotDTO> checkTimingList(String specialty) {
        List<TimingSlotEntity> list = restControllerRepository.checkTimingList(specialty);
        List<TimingSlotDTO> listDTO = new ArrayList<>();
        for(TimingSlotEntity timingSlotEntity:list){
            TimingSlotDTO timingSlotDTO = new TimingSlotDTO();
            BeanUtils.copyProperties(timingSlotEntity,timingSlotDTO);
            listDTO.add(timingSlotDTO);
        }
        return listDTO;
    }

    @Override
    public int checkDoctorSlotsAssign(String doctorEmail, String slotTime) {
        return restControllerRepository.checkDoctorSlotsAssign(doctorEmail,slotTime);

    }
}
