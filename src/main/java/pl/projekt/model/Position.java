package pl.projekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 21.11.2017.
 */
@Entity
@Table(name= "stanowisko")
public class Position {

    @Id
    @GeneratedValue
    private int id_stanowisko;
    private String nazwa;

    @Override
    public String toString() {
        return "Position{" +
                "id_stanowisko=" + id_stanowisko +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }

    public int getId_stanowisko() {
        return id_stanowisko;
    }

    public void setId_stanowisko(int id_stanowisko) {
        this.id_stanowisko = id_stanowisko;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
