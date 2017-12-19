package pl.projekt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import pl.projekt.dao.CategoryDAO;
import pl.projekt.dao.FormOfEmploymentDAO;
import pl.projekt.dao.PositionDAO;
import pl.projekt.model.Category;
import pl.projekt.model.FormOfEmployment;
import pl.projekt.model.Position;

import java.util.List;

/**
 * Created by jakub on 19.12.2017.
 */
@Transactional
@Component
public class FillListBox {

    private CategoryDAO categoryDAO;
    private FormOfEmploymentDAO formOfEmploymentDAO;
    private PositionDAO positionDAO;

    @Autowired
    public FillListBox(CategoryDAO categoryDAO, FormOfEmploymentDAO formOfEmploymentDAO, PositionDAO positionDAO) {
        this.categoryDAO = categoryDAO;
        this.formOfEmploymentDAO = formOfEmploymentDAO;
        this.positionDAO = positionDAO;
    }

    public void fillListBox(ModelAndView model) {
        populateModelCategory(model);
        populateModelFormOfEmployment(model);
        populateModelPosition(model);
    }

    private void populateModelCategory(ModelAndView model) {
        List<Category> category = categoryDAO.findAll();
        model.addObject("id_kategoria", category);
    }

    private void populateModelFormOfEmployment(ModelAndView model) {
        List<FormOfEmployment> formOfEmployments = formOfEmploymentDAO.findAll();
        model.addObject("id_forma_zatrudnienia", formOfEmployments);
    }

    private void populateModelPosition(ModelAndView model) {
        List<Position> positions = positionDAO.findAll();
        model.addObject("id_stanowisko", positions);
    }
}
