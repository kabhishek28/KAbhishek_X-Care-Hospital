package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class DoctorController {

    @Autowired
    HospitalService hospitalService;


    @RequestMapping("registerDoctor")
    public String getDoctorForm(){
        return "doctorRegisterForm";
    }

    @RequestMapping("doctorForm")
    public String saveDoctorFrom(DoctorDTO dto , Model model) throws IOException {
        model.addAttribute("dto",dto);
        hospitalService.saveDoctor(dto);
        return "doctorRegisterForm";
    }

    @GetMapping("download")
    public void previewImage(HttpServletResponse response, @RequestParam String imagePath) throws IOException {
        response.setContentType("image/jpeg");
        File file=new File("D:\\doctorfolder\\"+imagePath);
        InputStream inputStream=new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream servletOutputStream=response.getOutputStream();
        IOUtils.copy(inputStream,servletOutputStream);
        response.flushBuffer();
    }

    @RequestMapping("allDoctorsList")
    public String getUpDatePage(Model model){
        List<DoctorDTO> list = hospitalService.getAllDoctorsList();
        model.addAttribute("doctorsList",list);
        return "doctorsUpdatePage";
    }

    @RequestMapping("getDoctorUpdatePage")
    public String getUpdatePage(int doctorID,Model model){
        System.out.println(doctorID);
//        DoctorDTO doctorDTO = hospitalService.findSingleDoctorData(doctorID);
//        System.out.println(doctorDTO);
//        model.addAttribute("dto",doctorDTO);
        return "doctorUpDateFormPage";
    }
}
