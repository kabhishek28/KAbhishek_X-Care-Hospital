package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.dto.PatientsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
@Slf4j
public class PatientController {

    @RequestMapping
    public String getPatientsPage(){
        return "patients";
    }

    @RequestMapping("/save")
    public String savePatientsData(PatientsDTO patientsDTO){
        System.out.println(patientsDTO);
        return  "/patients";
    }

}
