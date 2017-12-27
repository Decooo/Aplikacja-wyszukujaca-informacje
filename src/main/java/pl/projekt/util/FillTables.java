package pl.projekt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.projekt.dao.CategoryDAO;
import pl.projekt.dao.FormOfEmploymentDAO;
import pl.projekt.dao.PositionDAO;
import pl.projekt.dao.UsersDAO;
import pl.projekt.model.*;

import java.util.List;

/**
 * Created by jakub on 27.12.2017.
 */
@Transactional
@Component
public class FillTables {

    private CategoryDAO categoryDAO;
    private FormOfEmploymentDAO formOfEmploymentDAO;
    private PositionDAO positionDAO;
    private UsersDAO usersDAO;

    @Autowired
    public FillTables(CategoryDAO categoryDAO, FormOfEmploymentDAO formOfEmploymentDAO, PositionDAO positionDAO, UsersDAO usersDAO) {
        this.categoryDAO = categoryDAO;
        this.formOfEmploymentDAO = formOfEmploymentDAO;
        this.positionDAO = positionDAO;
        this.usersDAO = usersDAO;
    }

    public void fillTables(List<Advertisement> adverts, List<Category> category, List<FormOfEmployment> formOfEmployments, List<Users> users, List<Position> positions) {
        for (Advertisement advert : adverts) {
            category.add(categoryDAO.findCategoryByID(advert.getId_kategoria()));
            formOfEmployments.add(formOfEmploymentDAO.findByID(advert.getId_forma_zatrudnienia()));
            users.add(usersDAO.findByID(advert.getId_uzytkownik()));
            positions.add(positionDAO.findByID(advert.getId_stanowisko()));
        }
    }
}
