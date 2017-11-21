package pl.projekt.daoimpl;

import pl.projekt.dao.PositionDAO;
import pl.projekt.model.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
public class PositionDAOImpl implements PositionDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.projekt.model");


    @Override
    public List<Position> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        List<Position> result = entityManager.createNativeQuery("SELECT * From stanowisko", Position.class).getResultList();
        entityManager.close();
        return result;

    }
}
