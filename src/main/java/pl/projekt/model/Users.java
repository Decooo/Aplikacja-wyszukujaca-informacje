package pl.projekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 20.11.2017.
 */
@Entity
@Table(name = "uzytkownik")
public class Users {

    @Id
    @GeneratedValue
    private int id_uzytkownik;
    private String login;
    private String haslo;

    @Override
    public String toString() {
        return "Users{" +
                "id_uzytkownik=" + id_uzytkownik +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                '}';
    }

    public int getId_uzytkownik() {
        return id_uzytkownik;
    }

    public void setId_uzytkownik(int id_uzytkownik) {
        this.id_uzytkownik = id_uzytkownik;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
