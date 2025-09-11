package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@Slf4j
public class HospitalRepositoryImp implements HospitalRepository{
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public AdminEntity checkAdmin(String gmail) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        AdminEntity adminEntity = new AdminEntity();
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
           Query query = entityManager.createNamedQuery("findAdminByGmail");
           query.setParameter("emailBy" , gmail);
           adminEntity = (AdminEntity) query.getSingleResult();
            entityTransaction.commit();


        }catch (NoResultException e) {
            return null;
        }catch (Exception e){
            if(entityTransaction.isActive()){
                entityTransaction.rollback();
            }
        }finally {
            entityManager.close();
        }
        return adminEntity;
    }
}
