package pl.projekt.model;

import javax.persistence.*;

/**
 * Created by jakub on 21.11.2017.
 */
@Entity
@Table(name= "kategoria")
public class Category {

    @Id
    @GeneratedValue
    private int id_kategoria;
    private String nazwa;

    @Override
    public String toString() {
        return "Category{" +
                "id_kategoria=" + id_kategoria +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }

    public int getId_kategoria() {
        return id_kategoria;
    }

    public void setId_kategoria(int id_kategoria) {
        this.id_kategoria = id_kategoria;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
