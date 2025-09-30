package com.xworkz.kabhishek_xcare_hospital.restcontroller;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.service.RestControllerSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    @Autowired
    RestControllerSercive restControllerSercive;

    @GetMapping("/checkEmail/{email}")
    public String checkmail(@PathVariable String email){
            int count=restControllerSercive.countEmail(email);
            if(count==0){
                return "Email not exists";
            }else{
                return "Email exists";
            }
    }

//    @GetMapping("/getDoctorName/{specialty}")
//    public String getName(@PathVariable String specialty, Model model){
//
//        List<DoctorDTO> doctors = restControllerSercive.checkDoctorList(specialty);
//        List<TimingSlotDTO> timingSlot = restControllerSercive.checkTimingList(specialty);
//
//
//        if (doctors.size()==0) {
//            model.addAttribute("message", "Doctor not exists");
//        } else {
//            model.addAttribute("doctors", doctors);
//            model.addAttribute("slots",timingSlot);
//            model.addAttribute("specialtyy",specialty);
//            model.addAttribute("selectedSpecialty", specialty);
//        }
//        return "";
//    }

    @GetMapping("/getDoctorSlots/{inputSlot}/{inputEmail}")
    public String getSlots(@PathVariable String inputSlot,@PathVariable String inputEmail){
        int count = restControllerSercive.checkDoctorSlotsAssign(inputEmail,inputSlot);
        if(count==0){
            return "";
        }else{
            return "Slot All ready Assign";
        }
    }

    @GetMapping("/getDoctorList/{specialty}")
    @ResponseBody
    public List<DoctorDTO> getDoctorList(@PathVariable String specialty, Model model){
        System.out.println(specialty);
        return restControllerSercive.checkDoctorList(specialty);
    }
}
