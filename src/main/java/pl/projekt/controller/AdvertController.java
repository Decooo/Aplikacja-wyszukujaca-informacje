package pl.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.projekt.dao.*;
import pl.projekt.model.Advertisement;
import pl.projekt.model.Category;
import pl.projekt.model.FormOfEmployment;
import pl.projekt.model.Position;

import java.util.List;

/**
 * Created by jakub on 21.11.2017.
 */
@Transactional
@Controller
@RequestMapping("/ogloszenia")
public class AdvertController {

    @Autowired
    AdvertisementDAO advertisementDAO;
    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    PositionDAO positionDAO;
    @Autowired
    FormOfEmploymentDAO formOfEmploymentDAO;
    @Autowired
    UsersDAO usersDAO;

    @RequestMapping(value = "/dodaj", method = RequestMethod.GET)
    public ModelAndView addAdvert() {
        ModelAndView model = new ModelAndView("newAdvertisement");
        model.addObject("advert", new Advertisement());

        populateModelCategory(model);
        populateModelFormOfEmployment(model);
        populateModelPosition(model);

        return model;
    }

    private void populateModelCategory(ModelAndView model){
        List<Category> category = categoryDAO.findAll();
        model.addObject("id_kategoria",category);
    }

    private void populateModelFormOfEmployment(ModelAndView model){
        List<FormOfEmployment> formOfEmployments= formOfEmploymentDAO.findAll();
        model.addObject("id_forma_zatrudnienia",formOfEmployments);
    }

    private void populateModelPosition(ModelAndView model){
        List<Position> positions = positionDAO.findAll();
        model.addObject("id_stanowisko",positions);
    }
}
