package pl.projekt.dao;

import pl.projekt.model.FormOfEmployment;

import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
public interface FormOfEmploymentDAO {
    List<FormOfEmployment> findAll();
}
