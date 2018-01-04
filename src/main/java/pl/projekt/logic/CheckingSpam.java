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

	public String checkingSpam(int userID, Advertisement advertisement) {
		List<Advertisement> ads = linearSearchWithGuard(userID);
		StopWords stopWords = new StopWords();
		if (ads.size() == 0) return "noSpam";
		System.out.println("ads.size() = " + ads.size());
		ads = stopWords.deleteStopWords(checkLocation(ads, advertisement));
		List<Advertisement> tempAdvertisement = new ArrayList<Advertisement>();
		tempAdvertisement.add(advertisement);
		tempAdvertisement = stopWords.deleteStopWords(tempAdvertisement);
		return algorithmTF_idf(ads, tempAdvertisement.get(0));
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

	private List<Advertisement> checkLocation(List<Advertisement> ads, Advertisement advertisement) {
		List<Advertisement> adsOtherLocation = new ArrayList<Advertisement>();
		for (Advertisement ad : ads) {
			if (!advertisement.getLokalizacja().equalsIgnoreCase(ad.getLokalizacja())) {
				adsOtherLocation.add(ad);
			}
		}
		return adsOtherLocation;
	}

	private String algorithmTF_idf(List<Advertisement> ads, Advertisement advertisement) {
		String[] description = advertisement.getOpis().split(" ");
		description = removeDuplicates(description);
		//

		return "noSpam";
	}

	private String[] removeDuplicates(String[] description) {
		List<String> arrWithoutDuplicates = new ArrayList<String>();
		if (description.length < 1) return description;
		boolean theSame = false;

		arrWithoutDuplicates.add(description[0]);
		for (int i = 1; i < description.length; i++) {
			theSame = false;
			description[i] = description[i].trim();
			for (String arrWithoutDuplicate : arrWithoutDuplicates) {
				if (description[i].equalsIgnoreCase(arrWithoutDuplicate)) {
					theSame = true;
					break;
				}
			}
			if (!theSame) arrWithoutDuplicates.add(description[i]);
		}

		String[] tabWithoutDuplicates = new String[arrWithoutDuplicates.size()];
		for (int i = 0; i < arrWithoutDuplicates.size(); i++) {
			tabWithoutDuplicates[i] = arrWithoutDuplicates.get(i);
		}
		return tabWithoutDuplicates;
	}
}
