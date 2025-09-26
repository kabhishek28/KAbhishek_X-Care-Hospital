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
    public String saveDoctorFrom(DoctorDTO dto , @RequestParam("photo") MultipartFile file, Model model) throws IOException {
        byte[] photoSize=file.getBytes();

        Path imagePath= Paths.get("D:\\doctorfolder\\"+dto.getDoctorName()+System.currentTimeMillis()+".jpg");
        Files.write(imagePath,photoSize);
        dto.setImagePath(imagePath.getFileName().toString());

        model.addAttribute("dto",dto);
        //hospitalService.saveDoctor(dto);
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
}
