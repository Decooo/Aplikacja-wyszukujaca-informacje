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

    public List<Advertisement> searchSimpleList(List<Advertisement> adverts, String salary, String location, int id_category,
                                                int id_position, int id_formOfEmployment) {
        List<Advertisement> foundAds = adverts;

        //podane zarobki
        if (!salary.equals("")) {
            halfDivision(foundAds, salary);
        }
        //podana kategoria
        if (id_category != 0) {
            tableAddress(foundAds);
        }
        //podana lokalziacja
        if (!location.equals("")) {
            findLocation(foundAds);
        }
        //podane stanowisko
        if (id_position != 0) {
            findPosition(foundAds);
        }
        //podana forma zatrudnienia
        if (id_formOfEmployment != 0) {
            findFormOfEmployment(foundAds);
        }

        return foundAds;
    }

    //podział połówkowy z modyfikacją kompletnego wyszukiwania
    private void halfDivision(List<Advertisement> foundAds, String salary) {
        String[] parts = salary.split("-");
        int minSalary = Integer.parseInt(parts[0]);
        int maxSalary = Integer.parseInt(parts[1]);
        foundAds = sortSalary(foundAds);

        int middleIndex = foundAds.size()/2;
        int middleSalary = foundAds.get(middleIndex).getZarobki();


        if(minSalary==middleSalary || (middleSalary>minSalary && middleSalary<maxSalary)){
            //sprawdzaj wartosc (size/2)-k do momentu az wartosc bedzie wieksza od maxSalary, gdzie k to ilosć iteracji
        }else if(maxSalary==middleSalary){
            //sprawdzaj wartość od (size/2)+k, do momentu az wartosć będzie mniejsza od minimalnej gdzie k to ilosc iteracji
        }else if(middleSalary>maxSalary){
            //przeszukaj 1 połowe tablicy do momentu az wartosc bedzie wieksza od maksymalnej
        }else if(middleSalary<minSalary){
            //przeszukaj drugą połowe tablicy do momentu az wartosc bedzie większa od maksymalnej
        }

    }

    //tablica adresowa ze względu na kategorie wyszukiwania
    private void tableAddress(List<Advertisement> foundAds) {
    }

    //wyszukiwanie po podanej lokalizacji
    private void findLocation(List<Advertisement> foundAds) {
    }

    //wyszukiwanie po podanym stanowisku
    private void findPosition(List<Advertisement> foundAds) {
    }

    //wyszukiwanie po podanej formie zatrudnienia
    private void findFormOfEmployment(List<Advertisement> foundAds) {
    }

    private List<Advertisement> sortSalary(List<Advertisement> adverts) {
        List<Advertisement> sortAdverts = new ArrayList<Advertisement>();
        int min;
        int index;
        while (adverts.isEmpty() == false) {
            index = 0;
            min = adverts.get(0).getZarobki();
            for (int i = 0; i < adverts.size(); i++) {
                if (adverts.get(i).getZarobki() < min) {
                    min = adverts.get(i).getZarobki();
                    index = i;
                }
            }
            sortAdverts.add(adverts.get(index));
            adverts.remove(index);
        }
        return sortAdverts;
    }
}
