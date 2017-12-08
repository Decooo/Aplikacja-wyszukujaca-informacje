package pl.projekt.dao;

import pl.projekt.model.Advertisement;

import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
public interface AdvertisementDAO {
    void add(Integer userId, Advertisement advertisement);
    List<Advertisement> findAll();
}
