package pl.projekt.logic;

import org.springframework.transaction.annotation.Transactional;
import pl.projekt.model.Advertisement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 02.01.2018.
 */
@Transactional
public class StopWords {

    public List<Advertisement> deleteStopWords(List<Advertisement> adverts) {
        ArrayList<Advertisement> doneAds = new ArrayList<Advertisement>();

        try {
            FileReader fileReader = new FileReader("stopwords.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < adverts.size(); i++) {
                String description = adverts.get(i).getOpis();
                description = replaceChars(description);
                String[] words = description.split(" ");
                while (bufferedReader.readLine() != null) {
                    for (int j = 0; j < words.length; i++) {
                        if (bufferedReader.readLine().equalsIgnoreCase(words[j])) {
                            words[j].replace(bufferedReader.readLine(), "");
                        }
                    }
                }
                for (int k = 0; k < words.length; k++) {
                    description = " " + words[k];
                }
                description.trim();
                adverts.get(i).setOpis(description);
                doneAds.add(adverts.get(i));
                System.out.println("description = " + description);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return doneAds;
    }

    private String replaceChars(String text) {
        text.replace(".", "");
        text.replace(",", "");
        text.replace(":", "");
        text.replace("(", "");
        text.replace(")", "");
        return text;
    }
}
