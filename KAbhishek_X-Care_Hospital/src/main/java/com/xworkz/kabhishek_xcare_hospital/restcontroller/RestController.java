package com.xworkz.kabhishek_xcare_hospital.restcontroller;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.DoctorSlotAssignmentDTO;
import com.xworkz.kabhishek_xcare_hospital.service.RestControllerSercive;
import com.xworkz.kabhishek_xcare_hospital.service.SlotAssignmentService;
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

    @Autowired
    SlotAssignmentService slotAssignmentService;

    @GetMapping("/checkEmail/{email}")
    public String checkmail(@PathVariable String email) {
        int count = restControllerSercive.countEmail(email);
        if (count == 0) {
            return "Email not exists";
        } else {
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
    public String getSlots(@PathVariable String inputSlot, @PathVariable String inputEmail) {
        int count = restControllerSercive.checkDoctorSlotsAssign(inputEmail, inputSlot);
        if (count == 0) {
            return "";
        } else {
            return "Slot All ready Assign";
        }
    }

    @GetMapping("/getDoctorList/{inputSpecialty}")
    @ResponseBody
    public String getDoctorList(@PathVariable String inputSpecialty) {
        List<DoctorDTO> doctorDTOS = restControllerSercive.checkDoctorList(inputSpecialty);

        // Build JSON manually
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < doctorDTOS.size(); i++) {
            DoctorDTO d = doctorDTOS.get(i);
            json.append("{")
                    .append("\"id\":").append(d.getId()).append(",")
                    .append("\"name\":\"").append(d.getDoctorName()).append("\",")
                    .append("\"specialty\":\"").append(d.getSpecialty()).append("\"")
                    .append("}");
            if (i < doctorDTOS.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }

    @RequestMapping("/getDoctorSlot/{inputDoctorID}")
    @ResponseBody
    public String getDoctorSlot(@PathVariable int inputDoctorID){
        try {
            List<String> slots = slotAssignmentService.getDoctorSlotsById(inputDoctorID);

            if (slots == null || slots.isEmpty()) {
                return "[]"; // Empty JSON array
            }

            // Build JSON manually
            StringBuilder json = new StringBuilder();
            json.append("[");
            for (int i = 0; i < slots.size(); i++) {
                json.append("\"").append(slots.get(i).replace("\"", "\\\"")).append("\""); // Escape quotes
                if (i < slots.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");

            return json.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "[]"; // Return empty JSON array on error
        }
    }
    }


