package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class RestControllerRepositoryImp implements RestControllerRespository{

    @Autowired
    EntityManagerFactory entityManagerFactory;
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
    public List<DoctorEntity> checkDoctorList(String specialty) {
        EntityManager eM = null;
        List<DoctorEntity> doctorList = new ArrayList<>();

        try {
            eM = entityManagerFactory.createEntityManager();
            Query query = eM.createNamedQuery("checkDoctorListBySpecialty");
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
    public List<TimingSlotEntity> checkTimingList(String specialty) {
        EntityManager eM = null;
        List<TimingSlotEntity> TimeList = new ArrayList<>();
        try {
            eM = entityManagerFactory.createEntityManager();
            Query query = eM.createNamedQuery("checkTimeSlotBySpecialty");
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
    public int checkDoctorSlotsAssign(String doctorEmail, String slotTime) {
        EntityManager eM = null;
        EntityTransaction eT = null;
        long count= 0L;
        int converted_count = 0;
        try {
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            Query query = eM.createNamedQuery("TimingSlotEntityCheckDoctorSlotExists");
            query.setParameter("doctorEmailBy",doctorEmail);
            query.setParameter("slotTimeBy",slotTime);
            count = (long) query.getSingleResult();
            converted_count=Math.toIntExact(count);
        }catch (Exception e){
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
        return converted_count;
    }
}
