package pl.projekt.dao;

import pl.projekt.model.Users;

/**
 * Created by jakub on 20.11.2017.
 */
public interface UsersDAO {

    Users findUser(String login);
}
