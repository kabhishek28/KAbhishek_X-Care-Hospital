package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.print.Doc;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class HospitalRepositoryImp implements HospitalRepository{
    @Autowired
    EntityManagerFactory entityManagerFactory;


    @Override
    public AdminEntity checkAdminExist(String gmail) {
        EntityManager entityManager = null;
        AdminEntity adminEntity = new AdminEntity();
        try{
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNamedQuery("findAdminByGmail");
            query.setParameter("em", gmail);
            adminEntity = (AdminEntity) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return adminEntity;
    }





    @Override
    public void saveOTP(String OTP, LocalDateTime localDateTime, HttpSession session) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {

            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("updateOTPByGmail");
            query.setParameter("emailby",session.getAttribute("email"));
            query.setParameter("dateTime",localDateTime);
            query.setParameter("otpby",OTP);
            query.executeUpdate();
            entityTransaction.commit();

        }catch (Exception e){
            e.printStackTrace();
            if(entityTransaction.isActive()){
                entityTransaction.rollback();
            }
        }finally {
            entityManager.close();
        }
    }

    @Override
    public AdminEntity getAdminEntity(String gmail) {
        EntityManager eM = null ;
        EntityTransaction eT = null;
        AdminEntity adminEntity = new AdminEntity();
        try{
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            Query query= eM.createNamedQuery("getEntityByName");
            query.setParameter("emailBy",gmail);
            adminEntity = (AdminEntity) query.getSingleResult();
            eT.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }

        return adminEntity;
    }







//    @Override
//    public String upDateDoctorAndSlots(String doctorEmail, String specialty,String timings, String startTime, String endTime) {
//        EntityManager eM = null;
//        EntityTransaction eT = null;
//        String message = "Update failed";
//        try{
//            eM = entityManagerFactory.createEntityManager();
//            eT = eM.getTransaction();
//            eT.begin();
//            Query query = eM.createNamedQuery("SetDoctorSlots");
//            query.setParameter("doctorEmailBy",doctorEmail);
//            query.setParameter("specialtyBy",specialty);
//            query.setParameter("slotTimingBy",timings);
//            int doctorUpDate = query.executeUpdate();
//
//            Query query1 = eM.createNamedQuery("updateSlotAssign");
//            query1.setParameter("startTimeBy",startTime);
//            query1.setParameter("endTimeBy",endTime);
//            int slotUpDate = query1.executeUpdate();
//
//            eT.commit();
//            message="Doctor updated: " + doctorUpDate + ", Slot updated: " + slotUpDate;
//
//        }catch (Exception e){
//            if(eT.isActive()){
//                eT.rollback();
//
//            }
//        }finally {
//            eM.close();
//        }
//        return message;
//    }















}
