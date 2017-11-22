package pl.projekt.daoimpl;

import pl.projekt.dao.AdvertisementDAO;
import pl.projekt.model.Advertisement;

import javax.persistence.*;

/**
 * Created by jakub on 21.11.2017.
 */
public class AdvertisementDAOImpl implements AdvertisementDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.projekt.model");

    @Override
    public void add(Integer userId, Advertisement advertisement) {
        EntityManager entityManager =emf.createEntityManager();

        StoredProcedureQuery query= entityManager.createStoredProcedureQuery("addAdverts", Advertisement.class)
                .registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2,Integer.class,ParameterMode.IN)
                .registerStoredProcedureParameter(3,Integer.class,ParameterMode.IN)
                .registerStoredProcedureParameter(4,Integer.class,ParameterMode.IN)
                .registerStoredProcedureParameter(5,String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(6,String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(7,Integer.class,ParameterMode.IN)
                .registerStoredProcedureParameter(8,String.class,ParameterMode.IN)
                .setParameter(1,userId)
                .setParameter(2,advertisement.getId_kategoria())
                .setParameter(3,advertisement.getId_forma_zatrudnienia())
                .setParameter(4,advertisement.getId_stanowisko())
                .setParameter(5,advertisement.getTytul())
                .setParameter(6,advertisement.getLokalizacja())
                .setParameter(7,advertisement.getZarobki())
                .setParameter(8,advertisement.getOpis());

        query.execute();
        entityManager.close();

    }
}
