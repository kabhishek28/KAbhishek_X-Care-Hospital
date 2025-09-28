package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.service.DoctorService;
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

    @Autowired
    DoctorService doctorService;


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
        DoctorDTO doctorDTO = hospitalService.findSingleDoctorData(doctorID);
        model.addAttribute("dto",doctorDTO);
        return "doctorUpDateFormPage";
    }

    @RequestMapping("updateDoctorForm")
    public String updateDoctordata(DoctorDTO doctorDTO,Model model) throws IOException {
        String value = hospitalService.saveUpdatedDoctorData(doctorDTO);
        if(!value.equals("Data Saved")){
            model.addAttribute("updatedDataNotSaved","Updated Doctor Data Not Saved");
            return "doctorUpDateFormPage";
        }else {
            model.addAttribute("updatedDataSaved","Updated Doctor Data Saved");
            return "doctorUpDateFormPage";
        }
    }

    @RequestMapping("deleteDoctor")
    public String deleteDoctor(int doctorID,Model model){
        String value = doctorService.deleteDoctorData(doctorID);
        if(!value.equals("data deleted")){
            model.addAttribute("dataNotDelete","Doctor Data not Deleted");
            return "";
        }else {
            model.addAttribute("dataDelete","Doctor Data Deleted");
            return "";
        }

    }
}
