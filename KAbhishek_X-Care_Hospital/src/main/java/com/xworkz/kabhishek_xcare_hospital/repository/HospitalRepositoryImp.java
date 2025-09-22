package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.constants.Specialty;
import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;
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
    public AdminEntity checkAdmin(String gmail) {
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
    public int countEmail(String email) {
        EntityManager entityManager = null;
        long count= 0L;
        int converted_count=0;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNamedQuery("countOfEmail");
            query.setParameter("emailBy", email);
            count = (long) query.getSingleResult();
            converted_count=Math.toIntExact(count);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return converted_count;
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
            System.out.println(doctorEntity);
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            eM.persist(doctorEntity);
            eT.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
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
}
