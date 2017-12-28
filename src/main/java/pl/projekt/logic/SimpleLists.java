package pl.projekt.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.projekt.dao.AdvertisementDAO;
import pl.projekt.model.Advertisement;
import pl.projekt.util.AdvertisementComparator;

import java.util.ArrayList;
import java.util.Collections;
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
            foundAds = halfDivision(foundAds, salary);
        }
        //podana kategoria
        if (id_category != 0) {
            foundAds = tableAddress(foundAds, id_category);
        }
        //podana lokalizacja
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
    private List<Advertisement> halfDivision(List<Advertisement> foundAds, String salary) {
        String[] parts = salary.split("-");
        int minSalary = Integer.parseInt(parts[0]);
        int maxSalary = Integer.parseInt(parts[1]);
        foundAds = sortSalary(foundAds);
        int middleIndex = foundAds.size() / 2;
        int middleSalary = foundAds.get(middleIndex).getZarobki();

        List<Advertisement> ads = new ArrayList<Advertisement>();
        if (minSalary == middleSalary || (middleSalary > minSalary && middleSalary < maxSalary)) {
            //sprawdzaj wartosc middleIndex-k do momentu az wartosc bedzie wieksza od maxSalary, gdzie k to ilosć iteracji
            int index = startingIndex(foundAds, middleIndex, middleSalary);
            while (index < foundAds.size() && foundAds.get(index).getZarobki() <= maxSalary) {
                ads.add(foundAds.get(index));
                index++;
            }
            return ads;
        } else if (maxSalary == middleSalary) {
            //sprawdzaj wartość od middleIndex+k, do momentu az wartosć będzie mniejsza od minimalnej gdzie k to ilosc iteracji
            int index = startingIndexFromTheEnd(foundAds, middleIndex, middleSalary);
            while (index > -1 && foundAds.get(index).getZarobki() >= minSalary) {
                ads.add(foundAds.get(index));
                index--;
            }
            return ads;
        } else if (middleSalary > maxSalary) {
            //przeszukaj 1 połowe tablicy do momentu az wartosc bedzie wieksza od maksymalnej
            int index = 0;
            while (index < foundAds.size() && foundAds.get(index).getZarobki() <= maxSalary) {
                if (foundAds.get(index).getZarobki() > minSalary) {
                    ads.add(foundAds.get(index));
                }
                index++;
            }
            return ads;
        } else if (middleSalary < minSalary) {
            //przeszukaj drugą połowe tablicy do momentu az wartosc bedzie większa od maksymalnej
            int index = middleIndex;
            while (index < foundAds.size() && foundAds.get(index).getZarobki() <= maxSalary) {
                if (foundAds.get(index).getZarobki() > minSalary) {
                    ads.add(foundAds.get(index));
                }
                index++;
            }
            return ads;
        }
        return foundAds;
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

    private int startingIndexFromTheEnd(List<Advertisement> foundAds, int middleIndex, int middleSalary) {
        int index = 1;
        do {
            if (foundAds.get(middleIndex + index).getZarobki() < middleSalary) {
                break;
            }
            index++;
        } while (index == middleIndex);

        if ((middleIndex + index - 1) == foundAds.size()) {
            return foundAds.size() - 1;
        } else return middleIndex + index - 1;
    }

    private int startingIndex(List<Advertisement> foundAds, int middleIndex, int middleSalary) {
        int index = 1;
        do {
            if (foundAds.get(middleIndex - index).getZarobki() < middleSalary) {
                break;
            }
            index++;
        } while (index == middleIndex);
        return middleIndex - index + 1;
    }

    //tablica adresowa ze względu na kategorie wyszukiwania
    private List<Advertisement> tableAddress(List<Advertisement> foundAds, int idCategory) {
        List<Advertisement> tempListAds = new ArrayList<Advertisement>();
        ArrayList<Integer> idCategories = new ArrayList<Integer>();
        int[][] tabIndex = new int[31][2];
        Collections.sort(foundAds, new AdvertisementComparator());
        if (foundAds.size() > 0) {
            idCategories.add(foundAds.get(0).getId_kategoria());
            tabIndex[0][0] = 0;
            for (int i = 1; i < foundAds.size(); i++) {
                if (foundAds.get(i).getId_kategoria() != idCategories.get(idCategories.size() - 1)) {
                    idCategories.add(foundAds.get(i).getId_kategoria());
                    tabIndex[idCategories.size() - 2][1] = i - 1;
                    tabIndex[idCategories.size() - 1][0] = i;
                }
            }
            tabIndex[idCategories.size() - 1][1] = foundAds.size() - 1;
        }
        int indexCategory = idCategories.indexOf(idCategory);
        if (indexCategory > -1) {
            for (int i = tabIndex[indexCategory][0]; i <= tabIndex[indexCategory][1]; i++) {
                tempListAds.add(foundAds.get(i));
            }
        }

        foundAds = tempListAds;
        return foundAds;
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


}
