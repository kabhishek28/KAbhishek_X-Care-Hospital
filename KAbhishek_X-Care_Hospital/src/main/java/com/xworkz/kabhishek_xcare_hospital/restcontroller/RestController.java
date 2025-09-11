package com.xworkz.kabhishek_xcare_hospital.restcontroller;

import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    @Autowired
    HospitalService service;
    @GetMapping("/checkEmail/{email}")
    public String checkmail(@PathVariable String email){
            int count=service.countEmail(email);
        System.out.println(email);
        System.out.println(count);
            if(count==0){
                return "Email not exists";
            }else{
                return "Email exists";
            }
    }

}
