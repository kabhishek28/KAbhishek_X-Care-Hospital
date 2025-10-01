package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.dto.PatientsDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.PatientsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository
@Slf4j
public class PatientsRepositoryImp implements PatientsRepository{

    @Autowired
    EntityManagerFactory entityManagerFactory;


    @Override
    public String savePatientsDetails(PatientsEntity patientsEntity) {
        EntityManager eM = null;
        EntityTransaction eT = null;
        try{
            eM = entityManagerFactory.createEntityManager();
            eT = eM.getTransaction();
            eT.begin();
            eM.persist(patientsEntity);
            eT.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(eT.isActive()){
                eT.rollback();
            }
        }finally {
            eM.close();
        }

        return "Patients Data Saved";
    }
}
