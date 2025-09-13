package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

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
}
