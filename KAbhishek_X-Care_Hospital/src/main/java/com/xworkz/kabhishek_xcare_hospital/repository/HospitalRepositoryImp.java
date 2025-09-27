package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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

    @Override
    public void saveDoctor(DoctorEntity doctorEntity) {
        EntityManager eM = null ;
        EntityTransaction eT = null;
        try {

            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            eM.persist(doctorEntity);
            eT.commit();
        }catch (Exception e){
            log.info(e.getMessage());
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
    }

    @Override
    public void saveTimingSlots(TimingSlotEntity timingSlotEntity) {
        EntityManager eM = null;
        EntityTransaction eT = null;
        try {
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            eM.persist(timingSlotEntity);
            eT.commit();
        }catch (Exception e){
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
    }

    @Override
    public List<DoctorEntity> findDoctorList(String specialty) {
        EntityManager eM = null;
        List<DoctorEntity> doctorList = new ArrayList<>();

        try {
            eM = entityManagerFactory.createEntityManager();
            Query query = eM.createNamedQuery("findDoctorListBySpecialty");
            query.setParameter("specialtyBy", specialty);
            doctorList = query.getResultList();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (eM != null) {
                eM.close();
            }
        }
        return doctorList;
    }

    @Override
    public List<TimingSlotEntity> findTimingList(String specialty) {
        EntityManager eM = null;
        List<TimingSlotEntity> TimeList = new ArrayList<>();
        try {
            eM = entityManagerFactory.createEntityManager();
            Query query = eM.createNamedQuery("getTimeSlotBySpecialty");
            query.setParameter("specialtyBy", specialty);
            TimeList = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (eM != null) {
                eM.close();
            }
        }
        return TimeList;
    }

    @Override
    public String upDateDoctorAndSlots(String doctorEmail, String specialty,String timings, String startTime, String endTime) {
        EntityManager eM = null;
        EntityTransaction eT = null;
        String message = "Update failed";
        try{
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            Query query = eM.createNamedQuery("SetDoctorSlots");
            query.setParameter("doctorEmailBy",doctorEmail);
            query.setParameter("specialtyBy",specialty);
            query.setParameter("slotTimingBy",timings);
            int doctorUpDate = query.executeUpdate();

            Query query1 = eM.createNamedQuery("updateSlotAssign");
            query1.setParameter("startTimeBy",startTime);
            query1.setParameter("endTimeBy",endTime);
            int slotUpDate = query1.executeUpdate();

            eT.commit();
            message="Doctor updated: " + doctorUpDate + ", Slot updated: " + slotUpDate;

        }catch (Exception e){
            if(eT.isActive()){
                eT.rollback();

            }
        }finally {
            eM.close();
        }
        return message;
    }



    @Override
    public List<DoctorEntity> getAllDoctorsList() {
        EntityManager eM = null;
        List<DoctorEntity> doctorList = new ArrayList<>();
        try {
            eM = entityManagerFactory.createEntityManager();
            Query query = eM.createNamedQuery("getAllDoctorsList");
            doctorList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (eM != null) {
                eM.close();
            }
        }
        return doctorList;
    }

    @Override
    public DoctorEntity findSingleDoctorData(String email) {
        EntityManager eM = null ;
        EntityTransaction eT = null;
        DoctorEntity doctorEntity = new DoctorEntity();
        try{
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            Query query= eM.createNamedQuery("getDoctorEntityByEmail");
            query.setParameter("emailBy",email);
            doctorEntity = (DoctorEntity) query.getSingleResult();
            eT.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
        return doctorEntity;
    }

    @Override
    public String saveUpdatedDoctorData(DoctorEntity doctorEntity) {
        EntityManager eM = null;
        EntityTransaction eT =null;
        String value = "Data not Saved";

        try{
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            eM.merge(doctorEntity);
            eT.commit();
            value = "Data Saved";
        }catch (Exception e){

            if(eT.isActive()){
                eT.rollback();
                e.printStackTrace();
            }
        }finally {
            eM.close();
        }
        return value;
    }

    @Override
    public DoctorEntity getDoctorEntityByID(int doctorID) {
        EntityManager eM = null ;
        EntityTransaction eT = null;
        DoctorEntity doctorEntity = new DoctorEntity();
        try{
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();

            doctorEntity = eM.find(DoctorEntity.class,doctorID);
            eT.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
        return doctorEntity;

    }

    @Override
    public TimingSlotEntity getTimingSlotEntityByID(int slotID) {
        EntityManager eM = null ;
        EntityTransaction eT = null;
        TimingSlotEntity timingSlotEntity = new TimingSlotEntity();
        try{
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            timingSlotEntity = eM.find(TimingSlotEntity.class,slotID);
            eT.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
        return timingSlotEntity;


    }


    @Override
    public String saveDoctorWithSlots(DoctorSlotAssignmentEntity doctorWithSlotsEntity) {
        EntityManager eM = null;
        EntityTransaction eT = null;
        try {
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            eM.persist(doctorWithSlotsEntity);
            eT.commit();
        }catch (Exception e){
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
        return "Data has been Saved";
    }

    @Override
    public String saveDoctorImageDetails(ImageEntity imageEntity) {
        EntityManager eM = null;
        EntityTransaction eT = null;
        try {
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            eM.persist(imageEntity);
            eT.commit();
        }catch (Exception e){
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
        return "Data has been Saved";
    }

}
