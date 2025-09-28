package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository
@Slf4j
public class DoctorRepositoryImp implements DoctorRepository{

    @Autowired
    EntityManagerFactory entityManagerFactory;
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
