package pl.projekt.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.projekt.dao.UsersDAO;
import pl.projekt.model.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
