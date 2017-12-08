package pl.projekt.dao;

import pl.projekt.model.Advertisement;

import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
public interface AdvertisementDAO {
    void add(Integer userId, Advertisement advertisement);
    List<Advertisement> findAll();
    List<Advertisement> findAllByID(Integer id_uzytkownika);

    void delete(int id);

    Advertisement findByID(int idAdvert);

    void update(int id_ogloszenia, int id_kategoria, int id_forma_zatrudnienia, int id_stanowisko, String tytul, String lokalizacja, int zarobki, String opis);
}
