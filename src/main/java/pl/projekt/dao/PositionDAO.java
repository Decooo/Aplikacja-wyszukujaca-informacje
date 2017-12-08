package pl.projekt.dao;

import pl.projekt.model.Position;

import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
public interface PositionDAO {
    List<Position> findAll();

    Position findByID(int id_stanowisko);
}
