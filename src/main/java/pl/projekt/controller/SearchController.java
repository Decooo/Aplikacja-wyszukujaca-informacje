package pl.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.projekt.dao.*;
import pl.projekt.model.*;
import pl.projekt.util.FillListBox;
import pl.projekt.validator.AdvertisementValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 18.12.2017.
 */
@Controller
@Transactional
@RequestMapping("/ogloszenia")
public class SearchController {

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
    @Autowired
    private AdvertisementValidator advertisementValidator;

    @InitBinder
    public void myInitBuilder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("target = " + target);
        if (target.getClass() == Advertisement.class) {
            dataBinder.setValidator(advertisementValidator);
        }
    }

    @RequestMapping(value = "/search")
    public ModelAndView fullTextSearch(@RequestParam(value = "inquiry") String inquiry,
                                       RedirectAttributes attributes, @ModelAttribute("search") Advertisement advertisement) {
        ModelAndView model = new ModelAndView();
        List<Advertisement> adverts = advertisementDAO.fullTextSearch(inquiry);
        if (adverts.size() < 1) {
            model.setViewName("redirect:/ogloszenia/lista");
            attributes.addFlashAttribute("css", "error");
            attributes.addFlashAttribute("msg", "Obecnie nie posiadamy ofert pracy spełniających podane kryteria");
            System.out.println("zapytanie = " + inquiry);
            return model;
        }

        List<Category> category = new ArrayList<Category>();
        List<FormOfEmployment> formOfEmployments = new ArrayList<FormOfEmployment>();
        List<Users> users = new ArrayList<Users>();
        List<Position> positions = new ArrayList<Position>();

        for (Advertisement advert : adverts) {
            category.add(categoryDAO.findCategoryByID(advert.getId_kategoria()));
            formOfEmployments.add(formOfEmploymentDAO.findByID(advert.getId_forma_zatrudnienia()));
            users.add(usersDAO.findByID(advert.getId_uzytkownik()));
            positions.add(positionDAO.findByID(advert.getId_stanowisko()));
        }

        model.addObject("category", category);
        model.addObject("formOfEmployments", formOfEmployments);
        model.addObject("users", users);
        model.addObject("positions", positions);
        model.addObject("adverts", adverts);
        model.addObject("inquiry", inquiry);
        model.setViewName("searchList");
        FillListBox fillListBox = new FillListBox(categoryDAO, formOfEmploymentDAO, positionDAO);
        fillListBox.fillListBox(model);
        System.out.println("inquiry = " + inquiry);

        return model;
    }

    @RequestMapping("/advancedSearch")
    public ModelAndView advancedSearch(@RequestParam(value = "inquiry", required = false) String inquiry,@ModelAttribute("search") Advertisement advertisement) {
        ModelAndView model = new ModelAndView();
        FillListBox fillListBox = new FillListBox(categoryDAO, formOfEmploymentDAO, positionDAO);
        fillListBox.fillListBox(model);

        model.setViewName("searchList");
        return model;
    }

}
