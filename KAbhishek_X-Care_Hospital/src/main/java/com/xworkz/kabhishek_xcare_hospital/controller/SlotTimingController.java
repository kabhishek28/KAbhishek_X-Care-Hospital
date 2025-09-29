package com.xworkz.kabhishek_xcare_hospital.controller;


import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;
import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import com.xworkz.kabhishek_xcare_hospital.service.SlotTimingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/")
@Slf4j
public class SlotTimingController {

    @Autowired
    SlotTimingService slotTimingService;

    @RequestMapping("setSlot")
    public String defineSlotTiming(){
        return "slotTiming";
    }

    @RequestMapping("saveSlotTiming")
    public String saveSlotTiming(TimingSlotDTO timingSlot ){

        LocalTime start = LocalTime.parse(timingSlot.getStartTime(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime end = LocalTime.parse(timingSlot.getEndTime(),DateTimeFormatter.ofPattern("HH:mm"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedStart = start.format(formatter);
        String formattedEnd = end.format(formatter);

        timingSlot.setStartTime(formattedStart);
        timingSlot.setEndTime(formattedEnd);

        slotTimingService.saveTimeSlots(timingSlot);
        return "slotTiming";
    }
}
