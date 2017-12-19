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

    public List<Advertisement> searchSimpleList(List<Advertisement> adverts, Advertisement advertisement) {
        List<Advertisement> foundAds = new ArrayList<Advertisement>();

        //podane zarobki
        if (advertisement.getZarobki()!=0) {
            halfDivision(foundAds, adverts);
        }
        //podana kategoria
        if (advertisement.getId_kategoria()!=0) {
            tableAddress(foundAds, adverts);
        }
        //podana lokalziacja
        if (advertisement.getLokalizacja()!=null) {
            findLocation(foundAds, adverts);
        }
        //podane stanowisko
        if (advertisement.getId_stanowisko()!=0) {
            findPosition(foundAds, adverts);
        }
        //podana forma zatrudnienia
        if (advertisement.getId_forma_zatrudnienia()!=0) {
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

    //wyszukiwanei po podanej formie zatrudnienia
    private void findFormOfEmployment(List<Advertisement> foundAds, List<Advertisement> adverts) {
    }
}
