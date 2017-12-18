package pl.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.projekt.dao.*;
import pl.projekt.model.*;
import pl.projekt.validator.AdvertisementValidator;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 18.12.2017.
 */
@Transactional
@Controller
@RequestMapping("/ogloszenia")
public class MyAdvertsController {

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

    @RequestMapping(value = "/moje")
    public ModelAndView myList(Principal principal) {
        ModelAndView model = new ModelAndView("myAdvertsList");
        Users user = usersDAO.findUser(principal.getName());
        List<Advertisement> myAdverts = advertisementDAO.findAllByID(user.getId_uzytkownik());
        model.addObject("adverts", myAdverts);

        List<Category> category = new ArrayList<Category>();
        List<FormOfEmployment> formOfEmployments = new ArrayList<FormOfEmployment>();
        List<Position> positions = new ArrayList<Position>();

        for (Advertisement advert : myAdverts) {
            category.add(categoryDAO.findCategoryByID(advert.getId_kategoria()));
            formOfEmployments.add(formOfEmploymentDAO.findByID(advert.getId_forma_zatrudnienia()));
            positions.add(positionDAO.findByID(advert.getId_stanowisko()));
        }

        model.addObject("category", category);
        model.addObject("formOfEmployments", formOfEmployments);
        model.addObject("positions", positions);

        return model;
    }


    @RequestMapping(value = "/usun/{id_ogloszenie}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id_ogloszenie") int id, RedirectAttributes attributes) {
        ModelAndView model = new ModelAndView("redirect:/ogloszenia/moje");
        advertisementDAO.delete(id);
        attributes.addFlashAttribute("css", "msgSuccess");
        attributes.addFlashAttribute("msg", "Ogloszenie zostało usunięte");
        return model;
    }

    @RequestMapping(value = "/edytuj/{id_ogloszenie}", method = RequestMethod.GET)
    public ModelAndView updateAdvert(@PathVariable("id_ogloszenie") int idAdvert) {
        ModelAndView model = new ModelAndView("updateAdvert");
        Advertisement advertisement = advertisementDAO.findByID(idAdvert);
        model.addObject("updateAdvert", advertisement);
        model.addObject("advertId", idAdvert);
        fillListBox(model);
        return model;
    }

    @RequestMapping(value = "updatesave", method = RequestMethod.POST)
    public ModelAndView update(ModelAndView m, @ModelAttribute("updateAdvert") @Validated Advertisement advertisement, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("updateAdvert");
        if (bindingResult.hasErrors()) {
            model.addObject("css", "error");
            model.addObject("msg", "Nie wprowadzono wszystkich danych lub wprowadzono je niepoprawnie!");
            fillListBox(model);
            return model;
        }
        advertisementDAO.update(advertisement.getId_ogloszenie(), advertisement.getId_kategoria(), advertisement.getId_forma_zatrudnienia(), advertisement.getId_stanowisko(), advertisement.getTytul(), advertisement.getLokalizacja(), advertisement.getZarobki(), advertisement.getOpis());
        model.setViewName("redirect:/ogloszenia/moje");
        return model;
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
