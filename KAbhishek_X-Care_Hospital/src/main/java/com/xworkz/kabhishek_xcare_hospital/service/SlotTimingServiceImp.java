package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;
import com.xworkz.kabhishek_xcare_hospital.repository.SlotTimingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SlotTimingServiceImp implements SlotTimingService{

    @Autowired
    SlotTimingRepository slotTimingRepository;

    @Override
    public String saveTimeSlots(TimingSlotDTO timingSlotDTO) {
        TimingSlotEntity timingSlotEntity = new TimingSlotEntity();
        BeanUtils.copyProperties(timingSlotDTO,timingSlotEntity);
        return slotTimingRepository.saveTimingSlots(timingSlotEntity);
    }
}
