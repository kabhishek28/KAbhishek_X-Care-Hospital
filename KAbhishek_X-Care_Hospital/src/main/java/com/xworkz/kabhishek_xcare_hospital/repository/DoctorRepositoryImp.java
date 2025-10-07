package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.ImageEntity;
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
public class DoctorRepositoryImp implements DoctorRepository{

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public int saveDoctor(DoctorEntity doctorEntity) {
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
        return doctorEntity.getId();
    }

    @Override
    public DoctorEntity findSingleDoctorData(int doctorID) {
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
        return "Updated Doctor Data Saved";
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
    public String saveUpdatedDoctorData(DoctorEntity doctorEntity) {
        EntityManager eM = null;
        EntityTransaction eT =null;
        String value = "Data not Saved";
        try{
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            eM.merge(doctorEntity);

            value = "data saved";
            eT.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
        return value;
    }

    @Override
    public String saveUpdatedDoctorImageDetails(ImageEntity imageEntity) {
        EntityManager eM = null;
        EntityTransaction eT =null;
        String value = "Data not Saved";

        try{
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            ImageEntity existing = eM.find(ImageEntity.class, imageEntity.getId());

            if (existing != null) {
                existing.setOriginalImageName(imageEntity.getOriginalImageName());
                existing.setImagePath(imageEntity.getImagePath());
                existing.setChangedName(imageEntity.getChangedName());
                value = "Data Updated";
            } else {
                value = "Data not Saved";
            }

            eT.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
        return value;
    }

    @Override
    public String deleteDoctorData(int doctorID) {
        EntityManager eM = null;
        EntityTransaction eT = null;
        String result = "Data not deleted";
        try {
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            DoctorEntity doctorEntity = eM.find(DoctorEntity.class,doctorID);
            if(doctorEntity != null){
                eM.remove(doctorEntity);
                result = "Data Deleted";
            }
            eT.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }
        return result;
    }
}
