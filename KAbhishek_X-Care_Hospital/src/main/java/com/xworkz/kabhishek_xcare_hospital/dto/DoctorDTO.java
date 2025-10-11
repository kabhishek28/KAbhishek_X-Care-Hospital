package com.xworkz.kabhishek_xcare_hospital.dto;

import com.xworkz.kabhishek_xcare_hospital.constants.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Invalid Data for Name")
    @Size(min=3 , max = 15 , message = "Name Size Between 3 to 15")
    @Pattern(regexp = "^[A-Za-z]+(\\s[A-Za-z]+)*$", message = "Numbers and special characters are not allowed")
    private String doctorName;

    @NotNull(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._]+@gmail\\.com$", message = "Enter a valid Gmail address (e.g., example@gmail.com)")
    private String doctorEmail;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number (must start with 6–9 and have 10 digits)")
    private long doctorPhoneNo;

    @NotBlank(message = "License/Registration number is required")
    @Pattern(regexp = "^[A-Za-z]{2,5}-?\\d{4,10}$", message = "Please enter a valid License No. (e.g., MC-123456)")
    private String license_number;

    @NotBlank(message = "Please select a specialty")
    private String specialty;

    @NotBlank(message = "Please select gender")
    private String doctorGender;

    @NotBlank(message = "Qualification is required")
    @Pattern(regexp = "^[A-Za-z.,\\s]{2,50}$", message = "Enter a valid qualification (e.g., MBBS, MD, MS)")
    private String qualification;

    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 60, message = "Experience cannot exceed 60 years")
    private int experience;

    private MultipartFile photo;
    private String imagePath;

}
