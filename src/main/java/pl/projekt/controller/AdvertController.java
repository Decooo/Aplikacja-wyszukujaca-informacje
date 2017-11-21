package pl.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.projekt.dao.*;
import pl.projekt.model.Advertisement;

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

        return model;
    }

}
