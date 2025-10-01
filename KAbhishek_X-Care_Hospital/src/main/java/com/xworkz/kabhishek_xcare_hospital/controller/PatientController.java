package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.dto.PatientsDTO;
import com.xworkz.kabhishek_xcare_hospital.service.PatientsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
@Slf4j
public class PatientController {

    @Autowired
    PatientsService patientsService;


    @RequestMapping
    public String getPatientsPage(){
        return "patients";
    }

    @RequestMapping("/save")
    public String savePatientsData(PatientsDTO patientsDTO, Model model){
        System.out.println(patientsDTO);
        String value = patientsService.savePatientsDetails(patientsDTO);
        if(!value.equals("Patients Data Saved")){
            model.addAttribute("DataNotSaved","Patients Data not Saved");
            return  "patients";
        }else {
            model.addAttribute("DataSaved","Patients Data Saved");
            return  "patients";
        }

    }

}
