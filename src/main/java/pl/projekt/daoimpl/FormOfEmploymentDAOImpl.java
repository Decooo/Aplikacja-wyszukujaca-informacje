package pl.projekt.daoimpl;

import pl.projekt.dao.FormOfEmploymentDAO;
import pl.projekt.model.FormOfEmployment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
public class FormOfEmploymentDAOImpl implements FormOfEmploymentDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.projekt.model");

    @Override
    public List<FormOfEmployment> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        List<FormOfEmployment> result = entityManager.createNativeQuery("SELECT * From forma_zatrudnienia", FormOfEmployment.class).getResultList();
        entityManager.close();
        return result;
    }

    @Override
    public FormOfEmployment findByID(int id_forma_zatrudnienia) {
        EntityManager entityManager = emf.createEntityManager();
        FormOfEmployment formOfEmployment = (FormOfEmployment) entityManager.createNativeQuery("SELECT * FROM forma_zatrudnienia WHERE id_forma_zatrudnienia='"+id_forma_zatrudnienia+"'",FormOfEmployment.class).getSingleResult();
        entityManager.close();
        return  formOfEmployment;
    }
}
