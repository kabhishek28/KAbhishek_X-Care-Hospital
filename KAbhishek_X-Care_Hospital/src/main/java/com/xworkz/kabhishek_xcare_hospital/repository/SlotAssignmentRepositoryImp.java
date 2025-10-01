package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorSlotAssignmentEntity;
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
public class SlotAssignmentRepositoryImp implements SlotAssignmentRepository {

    @Autowired
    EntityManagerFactory entityManagerFactory;

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
    public List<DoctorSlotAssignmentEntity> getDoctorSlotsById(int doctorID) {
        EntityManager eM = null;
        List<DoctorSlotAssignmentEntity> slotAssignmentEntities = new ArrayList<>();
        try {
            eM = entityManagerFactory.createEntityManager();
            String jpql = "SELECT d FROM DoctorSlotAssignmentEntity d " +
                    "JOIN FETCH d.doctorEntity doc " +
                    "JOIN FETCH d.timingSlotEntity t " +
                    "WHERE doc.id = :doctorID";

            slotAssignmentEntities = eM.createQuery(jpql, DoctorSlotAssignmentEntity.class)
                    .setParameter("doctorID", doctorID)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (eM != null) eM.close();
        }
        return slotAssignmentEntities;
    }

}
