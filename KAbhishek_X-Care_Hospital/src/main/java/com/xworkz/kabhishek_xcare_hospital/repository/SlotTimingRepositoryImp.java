package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository
@Slf4j
public class SlotTimingRepositoryImp implements SlotTimingRepository{

    @Autowired
    EntityManagerFactory entityManagerFactory;

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
}
