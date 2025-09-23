package com.xworkz.kabhishek_xcare_hospital.restcontroller;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;
import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    @Autowired
    HospitalService service;

    @GetMapping("/checkEmail/{email}")
    public String checkmail(@PathVariable String email){
            int count=service.countEmail(email);
            if(count==0){
                return "Email not exists";
            }else{
                return "Email exists";
            }
    }

    @GetMapping("/getDoctorName/{specialty}")
    public String getName(@PathVariable String specialty, Model model){
        System.out.println(specialty);
        List<DoctorDTO> doctors = service.checkDoctorList(specialty);
        List<TimingSlotDTO> timingSlot = service.checkTimingList(specialty);
        System.out.println(doctors);
        System.out.println(timingSlot);
        if (doctors.size()==0) {
            model.addAttribute("message", "Doctor not exists");
        } else {
            model.addAttribute("doctors", doctors);
            model.addAttribute("slots",timingSlot);
            model.addAttribute("specialtyy",specialty);
            model.addAttribute("selectedSpecialty", specialty);
        }

        return "";
    }

    @GetMapping("/getDoctorSlots/{inputSlot}/{inputEmail}")
    public String getSlots(@PathVariable String inputSlot,@PathVariable String inputEmail){
        System.out.println(inputEmail+"==="+inputSlot);
        int count = service.checkDoctorSlotsAssign(inputEmail,inputSlot);
        if(count==0){
            return "";
        }else{
            return "Slot All ready Assign";
        }
    }

}
