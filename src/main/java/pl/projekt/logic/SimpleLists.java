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
    static int[][] tableAddress = new int[31][2];
    static int sizeTableAddress = 0;

    @Autowired
    public SimpleLists(AdvertisementDAO advertisementDAO) {
        this.advertisementDAO = advertisementDAO;
    }

    public List<Advertisement> searchSimpleList(List<Advertisement> adverts,String salary, String location, int id_category,
                                                int id_position, int id_formOfEmployment) {
        List<Advertisement> foundAds = new ArrayList<Advertisement>();

        //podane zarobki
        if (!salary.equals("")) {
            halfDivision(foundAds, adverts);
        }
        //podana kategoria
        if (id_category!=0) {
            tableAddress(foundAds, adverts);
        }
        //podana lokalziacja
        if (!salary.equals("")) {
            findLocation(foundAds, adverts);
        }
        //podane stanowisko
        if (id_position!=0) {
            findPosition(foundAds, adverts);
        }
        //podana forma zatrudnienia
        if (id_formOfEmployment!=0) {
            findFormOfEmployment(foundAds, adverts);
        }

        return foundAds;
    }

    //podział połówkowy z modyfikacją kompletnego wyszukiwania
    private void halfDivision(List<Advertisement> foundAds, List<Advertisement> adverts) {
    }

    //tablica adresowa ze względu na kategorie wyszukiwania
    private void tableAddress(List<Advertisement> foundAds, List<Advertisement> adverts) {
    }

    //wyszukiwanie po podanej lokalizacji
    private void findLocation(List<Advertisement> foundAds, List<Advertisement> adverts) {
    }

    //wyszukiwanie po podanym stanowisku
    private void findPosition(List<Advertisement> foundAds, List<Advertisement> adverts) {
    }

    //wyszukiwanie po podanej formie zatrudnienia
    private void findFormOfEmployment(List<Advertisement> foundAds, List<Advertisement> adverts) {
    }

}
