package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.DoctorSlotAssignmentDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;
import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import com.xworkz.kabhishek_xcare_hospital.service.SlotAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class SlotAssignmentController {



    @Autowired
    SlotAssignmentService slotAssignmentService;

    @RequestMapping("assignSlot")
    public String assignSlotPage(){
        return "assignslot";
    }

    @RequestMapping("findDoctorsSlots")
    public String getDoctor(String specialty, Model model) {
        List<DoctorDTO> doctors = slotAssignmentService.findDoctorList(specialty);
        List<TimingSlotDTO> timingSlot = slotAssignmentService.findTimingList(specialty);

        if (doctors.size()==0) {
            model.addAttribute("message", "Doctor not exists");
        } else {
            model.addAttribute("doctors", doctors);
            model.addAttribute("slots",timingSlot);
            model.addAttribute("specialtyy",specialty);
            model.addAttribute("selectedSpecialty", specialty);
        }
        return "assignslot";
    }

    @RequestMapping("doctorSlotAssign")
    public String assignSlotWithDoctor(DoctorSlotAssignmentDTO doctorWithSlots, Model model){
        String value = slotAssignmentService.saveDoctorWithSlots(doctorWithSlots);

        if(!value.equals("Data has been Saved")){
            model.addAttribute("saveMessageError","Slot assignment failed.");
        }else {
            model.addAttribute("saveMessage","Slot assigned successfully.");
        }
        return "assignslot";
    }
}
