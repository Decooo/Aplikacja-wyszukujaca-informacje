package pl.projekt.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.projekt.dao.UsersDAO;
import pl.projekt.model.Users;

import javax.persistence.*;

/**
 * Created by jakub on 20.11.2017.
 */
public class UsersDAOImpl implements UsersDAO {

    @Autowired
    SessionFactory sessionFactory;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.projekt.model");

    @Override
    public Users findUser(String login) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            Users user = (Users) entityManager.createNativeQuery("Select * FROM uzytkownik WHERE login='" + login + "'", Users.class).getSingleResult();
            entityManager.close();
            return user;
        } catch (Exception e) {
            entityManager.close();
            return null;
        }
    }

    @Override
    public void add(String login, String haslo) {
        EntityManager entityManager = emf.createEntityManager();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("addusers",Users.class)
                .registerStoredProcedureParameter(1,String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2,String.class,ParameterMode.IN)
                .setParameter(1,login)
                .setParameter(2,haslo);
        query.execute();
        entityManager.close();
    }


}
