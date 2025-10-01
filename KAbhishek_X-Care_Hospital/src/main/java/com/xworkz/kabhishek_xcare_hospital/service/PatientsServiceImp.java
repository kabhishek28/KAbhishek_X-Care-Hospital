package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.PatientsDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.PatientsEntity;
import com.xworkz.kabhishek_xcare_hospital.repository.PatientsRepository;
import com.xworkz.kabhishek_xcare_hospital.repository.SlotAssignmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientsServiceImp implements PatientsService{
    @Autowired
    SlotAssignmentRepository slotAssignmentRepository;

    @Autowired
    PatientsRepository patientsRepository;

    @Override
    public String savePatientsDetails(PatientsDTO patientsDTO) {
        PatientsEntity patientsEntity = new PatientsEntity();
        DoctorEntity doctorEntity = slotAssignmentRepository.getDoctorEntityByID(patientsDTO.getDoctor());
        BeanUtils.copyProperties(patientsDTO,patientsEntity);
        patientsEntity.setDoctorEntity(doctorEntity);
        return patientsRepository.savePatientsDetails(patientsEntity);
    }
}
