package pl.projekt.dao;

import pl.projekt.model.Category;

import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
public interface CategoryDAO {
    List<Category> findAll();
}
