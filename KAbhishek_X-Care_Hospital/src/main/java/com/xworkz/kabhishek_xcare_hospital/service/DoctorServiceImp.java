package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.ImageEntity;
import com.xworkz.kabhishek_xcare_hospital.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DoctorServiceImp implements DoctorService{

    @Autowired
    DoctorRepository doctorRepository;


    @Override
    public void saveDoctor(DoctorDTO dto) throws IOException {
        DoctorEntity doctorEntity = new DoctorEntity();
        ImageEntity imageEntity = new ImageEntity();

        MultipartFile file = dto.getPhoto();
        byte[] photoSize=file.getBytes();
        Path imagePath= Paths.get("D:\\doctorfolder\\"+dto.getDoctorName()+System.currentTimeMillis()+".jpg");
        Files.write(imagePath,photoSize);

        BeanUtils.copyProperties(dto,doctorEntity);
        int doctorId = doctorRepository.saveDoctor(doctorEntity);

        imageEntity.setOriginalImageName(file.getOriginalFilename());
        imageEntity.setChangedName(imagePath.getFileName().toString());
        imageEntity.setImageSize(file.getSize());
        imageEntity.setImagePath(imagePath.toString());
        DoctorEntity doctor = doctorRepository.findSingleDoctorData(doctorId);
        imageEntity.setDoctorEntity(doctor);
        doctorRepository.saveDoctorImageDetails(imageEntity);
    }

    @Override
    public List<DoctorDTO> getAllDoctorsList() {
        List<DoctorEntity> doctorsList = doctorRepository.getAllDoctorsList();
        List<DoctorDTO> doctorsDTOList = new ArrayList<>();

        for(DoctorEntity doctor:doctorsList){

            ImageEntity imageEntity = doctor.getImageEntity();
            if(imageEntity == null){
                DoctorDTO doctorDTO = new DoctorDTO();
                BeanUtils.copyProperties(doctor,doctorDTO);
                doctorsDTOList.add(doctorDTO);
            }else {
                DoctorDTO doctorDTO = new DoctorDTO();
                String imagePath = doctor.getImageEntity().getChangedName();
                BeanUtils.copyProperties(doctor,doctorDTO);
                doctorDTO.setImagePath(imagePath);
                doctorsDTOList.add(doctorDTO);
            }
        }
        return doctorsDTOList;
    }

    @Override
    public String saveUpdatedDoctorData(DoctorDTO doctorDTO) throws IOException {
        DoctorEntity doctorEntity = new DoctorEntity();
        ImageEntity imageEntity = new ImageEntity();

        MultipartFile file = doctorDTO.getPhoto();
        byte[] photoSize = file.getBytes();
        Path imagePath = Paths.get("D:\\doctorfolder\\"+doctorDTO.getDoctorName()+System.currentTimeMillis()+".jpg");
        Files.write(imagePath,photoSize);

        BeanUtils.copyProperties(doctorDTO,doctorEntity);
        String value = doctorRepository.saveUpdatedDoctorData(doctorEntity);

        DoctorEntity doctor = doctorRepository.findSingleDoctorData(doctorDTO.getId());

        imageEntity.setId(doctor.getImageEntity().getId());
        imageEntity.setOriginalImageName(file.getOriginalFilename());
        imageEntity.setChangedName(imagePath.getFileName().toString());
        imageEntity.setImageSize(file.getSize());
        imageEntity.setImagePath(imagePath.toString());
        imageEntity.setDoctorEntity(doctor);
        String value1 =  doctorRepository.saveUpdatedDoctorImageDetails(imageEntity);

        if (!value.equals("data saved") && !value1.equals("Updated Doctor Data Saved")) {
            return "Data not Saved";
        }else {
            return  "Data Saved";
        }
    }


    @Override
    public DoctorDTO findSingleDoctorData(int doctorID) {
        DoctorEntity doctorEntity = doctorRepository.findSingleDoctorData(doctorID);
        ImageEntity imageEntity = doctorEntity.getImageEntity();
        DoctorDTO doctorDTO = new DoctorDTO();
        if(imageEntity == null){
            BeanUtils.copyProperties(doctorEntity,doctorDTO);
        }else {
            BeanUtils.copyProperties(doctorEntity,doctorDTO);
            doctorDTO.setImagePath(imageEntity.getChangedName());
        }
        return doctorDTO;
    }

    @Override
    public String deleteDoctorData(int doctorID) {
        return doctorRepository.deleteDoctorData(doctorID);
    }
}
