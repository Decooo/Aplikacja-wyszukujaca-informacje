package pl.projekt.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.projekt.dao.AdvertisementDAO;
import pl.projekt.model.Advertisement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 18.12.2017.
 */

@Transactional
public class SimpleLists {
//podział połówkowy z własną modyfikacją -> zarobki
//grupowanie obiektów tablicą adresową -> kategoria

    private AdvertisementDAO advertisementDAO;


    @Autowired
    public SimpleLists(AdvertisementDAO advertisementDAO) {
        this.advertisementDAO = advertisementDAO;
    }

    public List<Advertisement> searchSimpleList(List<Advertisement> adverts){
        List<Advertisement> foundAds = new ArrayList<Advertisement>();

    return foundAds;
    }



    public int quantity() {
        return advertisementDAO.numbersRecords();
    }



}
