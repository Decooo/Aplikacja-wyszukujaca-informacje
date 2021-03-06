package pl.projekt.daoimpl;

import pl.projekt.dao.AdvertisementDAO;
import pl.projekt.model.Advertisement;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
public class AdvertisementDAOImpl implements AdvertisementDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.projekt.model");

    @Override
    public void add(Integer userId, Advertisement advertisement) {
        EntityManager entityManager = emf.createEntityManager();

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("addAdverts", Advertisement.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                .setParameter(1, userId)
                .setParameter(2, advertisement.getId_kategoria())
                .setParameter(3, advertisement.getId_forma_zatrudnienia())
                .setParameter(4, advertisement.getId_stanowisko())
                .setParameter(5, advertisement.getTytul())
                .setParameter(6, advertisement.getLokalizacja())
                .setParameter(7, advertisement.getZarobki())
                .setParameter(8, advertisement.getOpis());

        query.execute();
        entityManager.close();

    }

    @Override
    public List<Advertisement> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        List<Advertisement> adverts = entityManager.createNativeQuery("SELECT * FROM ogloszenie", Advertisement.class).getResultList();
        entityManager.close();
        return adverts;
    }

    @Override
    public List<Advertisement> findAllByID(Integer id_uzytkownika) {
        EntityManager entityManager = emf.createEntityManager();
        List<Advertisement> adverts = (List<Advertisement>) entityManager.createNativeQuery("SELECT * FROM ogloszenie WHERE id_uzytkownik='" + id_uzytkownika + "'", Advertisement.class).getResultList();
        entityManager.close();
        return adverts;
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = emf.createEntityManager();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("deleteAdvert", Advertisement.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, id);
        query.execute();
        entityManager.close();
    }

    @Override
    public Advertisement findByID(int idAdvert) {
        EntityManager entityManager = emf.createEntityManager();
        Advertisement advertisement = (Advertisement) entityManager.createNativeQuery("SELECT * FROM ogloszenie WHERE id_ogloszenie='" + idAdvert + "'", Advertisement.class).getSingleResult();
        entityManager.close();

        return advertisement;
    }

    @Override
    public void update(int id_ogloszenie, int id_kategoria, int id_forma_zatrudnienia, int id_stanowisko, String tytul, String lokalizacja, int zarobki, String opis) {
        EntityManager entityManager = emf.createEntityManager();
        System.out.println("id_ogloszenie = " + id_ogloszenie);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateAdverts", Advertisement.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(8, Integer.class, ParameterMode.IN)
                .setParameter(1, id_kategoria)
                .setParameter(2, id_forma_zatrudnienia)
                .setParameter(3, id_stanowisko)
                .setParameter(4, tytul)
                .setParameter(5, lokalizacja)
                .setParameter(6, zarobki)
                .setParameter(7, opis)
                .setParameter(8, id_ogloszenie);

        query.execute();
        entityManager.close();

    }

    @Override
    public List<Advertisement> findAllPagintaion(int page) {
        System.out.println("page = " + page);
        EntityManager entityManager = emf.createEntityManager();
        List adverts = entityManager.createNativeQuery("SELECT * FROM ogloszenie", Advertisement.class)
                .setFirstResult(page * 15 - 15)
                .setMaxResults(15)
                .getResultList();
        entityManager.close();
        return adverts;
    }

    @Override
    public List<Advertisement> fullTextSearch(String data) {
        EntityManager entityManager = emf.createEntityManager();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("fullTextSearch", Advertisement.class)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .setParameter(1, data);
        List<Advertisement> foundAds = query.getResultList();
        entityManager.close();
        return foundAds;
    }

    @Override
    public int numbersRecords() {
        EntityManager entityManager =emf.createEntityManager();
        Integer quantity =0;
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("numbersRecords",Advertisement.class)
                .registerStoredProcedureParameter(1,Integer.class,ParameterMode.OUT);
        query.execute();
        quantity = (Integer) query.getOutputParameterValue(1);
        entityManager.close();
        return quantity;
    }

}
