package pl.projekt.logic;

import org.springframework.transaction.annotation.Transactional;
import pl.projekt.model.Advertisement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 02.01.2018.
 */
@Transactional
public class StopWords {

    public List<Advertisement> deleteStopWords(List<Advertisement> adverts) {
        ArrayList<Advertisement> doneAds = new ArrayList<Advertisement>();

        String[] stopWords = listStopWords();
        for (int i = 0; i < adverts.size(); i++) {
            String description = adverts.get(i).getOpis();
            description = replaceChars(description);
            String[] words = description.split(" ");
            {
                for (String stopWord1 : stopWords) {
                    for (int j = 0; j < words.length; j++) {
                        if (stopWord1.equalsIgnoreCase(words[j])) {
                            words[j] = "";
                        }
                    }
                }
            }
            description = "";
            for (String word : words) {
                if(!word.equalsIgnoreCase("")){
                    description = description + " " + word;
                }
            }
            description=description.trim();
            adverts.get(i).setOpis(description);
            doneAds.add(adverts.get(i));
        }
        return doneAds;
    }

    private String replaceChars(String text) {
        text = text.replace(".", "");
        text = text.replace(",", "");
        text = text.replace(":", "");
        text = text.replace("(", "");
        text = text.replace(")", "");
        text = text.replace("*", "");
        text = text.replace("!", "");
        text = text.replace("-", "");
        text = text.replace("?", "");

        return text;
    }

    private String[] listStopWords() {
        String[] list = "a,aby,ach,acz,aczkolwiek,aj,albo,ale,alez,ależ,ani,az,aż,bardziej,bardzo,beda,bedzie,bez,deda,będą,bede,będę,będzie,bo,bowiem,by,byc,być,byl,byla,byli,bylo,byly,był,była,było,były,bynajmniej,cala,cali,caly,cała,cały,ci,cie,ciebie,cię,co,cokolwiek,cos,coś,czasami,czasem,czemu,czy,czyli,daleko,dla,dlaczego,dlatego,do,dobrze,dokad,dokąd,dosc,dość,duzo,dużo,dwa,dwaj,dwie,dwoje,dzis,dzisiaj,dziś,gdy,gdyby,gdyz,gdyż,gdzie,gdziekolwiek,gdzies,gdzieś,go,i,ich,ile,im,inna,inne,inny,innych,iz,iż,ja,jak,jakas,jakaś,jakby,jaki,jakichs,jakichś,jakie,jakis,jakiś,jakiz,jakiż,jakkolwiek,jako,jakos,jakoś,ją,je,jeden,jedna,jednak,jednakze,jednakże,jedno,jego,jej,jemu,jesli,jest,jestem,jeszcze,jeśli,jezeli,jeżeli,juz,już,kazdy,każdy,kiedy,kilka,kims,kimś,kto,ktokolwiek,ktora,ktore,ktorego,ktorej,ktory,ktorych,ktorym,ktorzy,ktos,ktoś,która,które,którego,której,który,których,którym,którzy,ku,lat,lecz,lub,ma,mają,mało,mam,mi,miedzy,między,mimo,mna,mną,mnie,moga,mogą,moi,moim,moj,moja,moje,moze,mozliwe,mozna,może,możliwe,można,mój,mu,musi,my,na,nad,nam,nami,nas,nasi,nasz,nasza,nasze,naszego,naszych,natomiast,natychmiast,nawet,nia,nią,nic,nich,nie,niech,niego,niej,niemu,nigdy,nim,nimi,niz,niż,no,o,obok,od,około,on,ona,one,oni,ono,oraz,oto,owszem,pan,pana,pani,po,pod,podczas,pomimo,ponad,oniewaz,ponieważ,powinien,powinna,powinni,powinno,poza,prawie,przeciez,przecież,przed,przede,przedtem,przez,przy,roku,rowniez,również,sam,sama,są,sie,się,skad,skąd,soba,sobą,sobie,sposob,sposób,swoje,ta,tak,taka,taki,takie,takze,także,tam,te,tego,tej,ten,teraz,też,to,toba,tobą,tobie,totez,toteż,totobą,trzeba,tu,tutaj,twoi,twoim,twoj,twoja,twoje,twój,twym,ty,tych,tylko,tym,u,w,wam,wami,was,wasz,wasza,wasze,we,według,wiele,wielu,więc,więcej,wlasnie,właśnie,wszyscy,wszystkich,wszystkie,wszystkim,wszystko,wtedy,wy,z,za,zaden,zadna,zadne,zadnych,zapewne,zawsze,ze,zeby,zeznowu,zł,znow,znowu,znów,zostal,został,żaden,żadna,żadne,żadnych,że,żeby".split(",");
        return list;
    }

}
