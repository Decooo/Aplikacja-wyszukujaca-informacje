package pl.projekt.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.projekt.dao.AdvertisementDAO;
import pl.projekt.model.Advertisement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 02.01.2018.
 */
@Transactional
public class CheckingSpam {

    private AdvertisementDAO advertisementDAO;

    @Autowired
    public CheckingSpam(AdvertisementDAO advertisementDAO) {
        this.advertisementDAO = advertisementDAO;
    }

    public String checkingSpam(int userID) {
        List<Advertisement> ads = linearSearchWithGuard(userID);
        StopWords stopWords = new StopWords();
        if (ads.size() == 0) return "noSpam";
        System.out.println("ads.size() = " + ads.size());
        ads=stopWords.deleteStopWords(ads);
        return algorithmTF_idf(ads);
    }


    //przeszukiwanie z wartownikiem
    private List<Advertisement> linearSearchWithGuard(int userID) {
        List<Advertisement> adverts = new ArrayList<Advertisement>();
        List<Advertisement> foundAds = new ArrayList<Advertisement>();
        adverts = advertisementDAO.findAllByID(userID);
        if (adverts.size() == 0) return adverts;
        adverts.add(new Advertisement(userID, 0, 0, 0, "guard", "guard", 0, "guard"));
        int i = 0;
        while (true) {
            if (adverts.get(i).getId_uzytkownik() == userID) {
                if (i == adverts.size() - 1) {
                    break;
                }
                foundAds.add(adverts.get(i));
            }
            i++;
        }

        return foundAds;
    }

    private String algorithmTF_idf(List<Advertisement> ads) {



        return "noSpam";
    }
}
