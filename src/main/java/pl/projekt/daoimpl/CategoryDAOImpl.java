package pl.projekt.daoimpl;

import pl.projekt.dao.CategoryDAO;
import pl.projekt.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
public class CategoryDAOImpl implements CategoryDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.projekt.model");

    @Override
    public List<Category> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        List<Category> result = entityManager.createNativeQuery("SELECT * From kategoria", Category.class).getResultList();
        entityManager.close();
        return result;
    }
}
