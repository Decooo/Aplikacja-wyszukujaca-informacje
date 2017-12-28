package pl.projekt.util;

import pl.projekt.model.Advertisement;

import java.util.Comparator;

/**
 * Created by jakub on 28.12.2017.
 */
public class AdvertisementComparator implements Comparator<Advertisement> {
    @Override
    public int compare(Advertisement o1, Advertisement o2) {
        if(o1.getId_kategoria()>o2.getId_kategoria())
            return 1;
        if(o1.getId_kategoria()<o2.getId_kategoria())
            return -1;
        return 0;
    }
}
