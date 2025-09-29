package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.DoctorSlotAssignmentDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorSlotAssignmentEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;
import com.xworkz.kabhishek_xcare_hospital.repository.SlotAssignmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SlotAssignmentServiceImp implements SlotAssignmentService{

    @Autowired
    SlotAssignmentRepository slotAssignmentRepository;

    @Override
    public List<DoctorDTO> findDoctorList(String specialty) {

        List<DoctorEntity> list =  slotAssignmentRepository.findDoctorList(specialty);
        List<DoctorDTO> doctors = new ArrayList<>();

        for (DoctorEntity doctorEntity : list){
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctorEntity,doctorDTO);
            doctors.add(doctorDTO);
        }
        return doctors;
    }

    @Override
    public List<TimingSlotDTO> findTimingList(String specialty) {
        List<TimingSlotEntity> list = slotAssignmentRepository.findTimingList(specialty);
        List<TimingSlotDTO> listDTO = new ArrayList<>();
        for(TimingSlotEntity timingSlotEntity:list){
            TimingSlotDTO timingSlotDTO = new TimingSlotDTO();
            BeanUtils.copyProperties(timingSlotEntity,timingSlotDTO);
            listDTO.add(timingSlotDTO);
        }
        return listDTO;
    }

    @Override
    public String saveDoctorWithSlots(DoctorSlotAssignmentDTO doctorWithSlots) {
        DoctorEntity doctorEntity = slotAssignmentRepository.getDoctorEntityByID(doctorWithSlots.getDoctorID());
        TimingSlotEntity timingSlotEntity = slotAssignmentRepository.getTimingSlotEntityByID(doctorWithSlots.getSlotID());

        DoctorSlotAssignmentEntity doctorSlotAssignmentEntity = new DoctorSlotAssignmentEntity();
        doctorSlotAssignmentEntity.setId(doctorWithSlots.getId());
        doctorSlotAssignmentEntity.setOptionDoctorName(doctorWithSlots.getOptionDoctorName());
        doctorSlotAssignmentEntity.setDoctorEmail(doctorWithSlots.getDoctorEmail());
        doctorSlotAssignmentEntity.setSpecialty(doctorWithSlots.getSpecialty());
        doctorSlotAssignmentEntity.setTimings(doctorWithSlots.getTimings());
        doctorSlotAssignmentEntity.setDoctorEntity(doctorEntity);
        doctorSlotAssignmentEntity.setTimingSlotEntity(timingSlotEntity);

        return slotAssignmentRepository.saveDoctorWithSlots(doctorSlotAssignmentEntity);
    }
}
