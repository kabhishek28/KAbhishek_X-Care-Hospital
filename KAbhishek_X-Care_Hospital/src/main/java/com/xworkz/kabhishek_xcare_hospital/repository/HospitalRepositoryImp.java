package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
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
            System.out.println(gmail);
            Query query = entityManager.createNamedQuery("findAdminByGmail");
            query.setParameter("em", gmail);
            adminEntity = (AdminEntity) query.getSingleResult();
            System.out.println(adminEntity);

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
}
