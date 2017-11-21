package pl.projekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 21.11.2017.
 */
@Entity
@Table(name = "forma_zatrudniena")
public class FormOfEmployment {

    @Id
    @GeneratedValue
    private int id_forma_zatrudnienia;
    private String nazwa;

    @Override
    public String toString() {
        return "FormOfEmployment{" +
                "id_forma_zatrudnienia=" + id_forma_zatrudnienia +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }

    public int getId_forma_zatrudnienia() {
        return id_forma_zatrudnienia;
    }

    public void setId_forma_zatrudnienia(int id_forma_zatrudnienia) {
        this.id_forma_zatrudnienia = id_forma_zatrudnienia;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
