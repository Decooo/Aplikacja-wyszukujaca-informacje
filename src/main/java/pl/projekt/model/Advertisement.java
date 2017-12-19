package pl.projekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 21.11.2017.
 */
@Entity
@Table(name = "ogloszenie")
public class Advertisement {

    @Id
    @GeneratedValue
    private int id_ogloszenie;
    private int id_uzytkownik;
    private int id_kategoria;
    private int id_forma_zatrudnienia;
    private int id_stanowisko;
    private String tytul;
    private String lokalizacja;
    private int zarobki;
    private String opis;

    public Advertisement(int id_uzytkownik, int id_kategoria, int id_forma_zatrudnienia, int id_stanowisko, String tytul, String lokalizacja, int zarobki, String opis) {
        this.id_uzytkownik = id_uzytkownik;
        this.id_kategoria = id_kategoria;
        this.id_forma_zatrudnienia = id_forma_zatrudnienia;
        this.id_stanowisko = id_stanowisko;
        this.tytul = tytul;
        this.lokalizacja = lokalizacja;
        this.zarobki = zarobki;
        this.opis = opis;
    }

    public Advertisement() {
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id_ogloszenie=" + id_ogloszenie +
                ", id_uzytkownik=" + id_uzytkownik +
                ", id_kategoria=" + id_kategoria +
                ", id_forma_zatrudnienia=" + id_forma_zatrudnienia +
                ", id_stanowisko=" + id_stanowisko +
                ", tytul='" + tytul + '\'' +
                ", lokalizacja='" + lokalizacja + '\'' +
                ", zarobki=" + zarobki +
                ", opis='" + opis + '\'' +
                '}';
    }

    public int getId_ogloszenie() {
        return id_ogloszenie;
    }

    public void setId_ogloszenie(int id_ogloszenie) {
        this.id_ogloszenie = id_ogloszenie;
    }

    public int getId_uzytkownik() {
        return id_uzytkownik;
    }

    public void setId_uzytkownik(int id_uzytkownik) {
        this.id_uzytkownik = id_uzytkownik;
    }

    public int getId_kategoria() {
        return id_kategoria;
    }

    public void setId_kategoria(int id_kategoria) {
        this.id_kategoria = id_kategoria;
    }

    public int getId_forma_zatrudnienia() {
        return id_forma_zatrudnienia;
    }

    public void setId_forma_zatrudnienia(int id_forma_zatrudnienia) {
        this.id_forma_zatrudnienia = id_forma_zatrudnienia;
    }

    public int getId_stanowisko() {
        return id_stanowisko;
    }

    public void setId_stanowisko(int id_stanowisko) {
        this.id_stanowisko = id_stanowisko;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja = lokalizacja;
    }

    public int getZarobki() {
        return zarobki;
    }

    public void setZarobki(int zarobki) {
        this.zarobki = zarobki;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
