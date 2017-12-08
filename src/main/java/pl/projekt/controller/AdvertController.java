package pl.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.projekt.dao.*;
import pl.projekt.model.*;
import pl.projekt.validator.AdvertisementValidator;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
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
    @Autowired
    private AdvertisementValidator advertisementValidator;

    @InitBinder
    public void myInitBuilder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null){
            return;
        }
        System.out.println("target = " + target);
        if(target.getClass() == Advertisement.class){
            dataBinder.setValidator(advertisementValidator);
        }
    }

    @RequestMapping(value = "/dodaj", method = RequestMethod.GET)
    public ModelAndView addAdvert() {
        ModelAndView model = new ModelAndView("newAdvertisement");
        model.addObject("advert", new Advertisement());

        fillListBox(model);

        return model;
    }

    @RequestMapping(value = "/lista")
    public ModelAndView list(){
        ModelAndView model = new ModelAndView("advertsList");
        List<Advertisement> adverts = advertisementDAO.findAll();
        model.addObject("adverts", adverts);

        List<Category> category = new ArrayList<Category>();
        List<FormOfEmployment> formOfEmployments = new ArrayList<FormOfEmployment>();
        List<Users> users = new ArrayList<Users>();
        List<Position> positions = new ArrayList<Position>();

        for(Advertisement advert : adverts){
            category.add(categoryDAO.findCategoryByID(advert.getId_kategoria()));
            formOfEmployments.add(formOfEmploymentDAO.findByID(advert.getId_forma_zatrudnienia()));
            users.add(usersDAO.findByID(advert.getId_uzytkownik()));
            positions.add(positionDAO.findByID(advert.getId_stanowisko()));
        }

        model.addObject("category",category);
        model.addObject("formOfEmployments", formOfEmployments);
        model.addObject("users",users);
        model.addObject("positions",positions);
        return model;
    }

    private void fillListBox(ModelAndView model){
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

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(Principal principal, HttpServletRequest request, ModelAndView m, @ModelAttribute("advert") @Validated Advertisement advertisement, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("newAdvertisement");

        if(bindingResult.hasErrors()){
            fillListBox(model);
            model.addObject("css","error");
            model.addObject("msg","Nie wprowadzono wszystkich danych lub wprowadzono je niepoprawnie");
        }else {

            Users user = usersDAO.findUser(principal.getName());

            advertisementDAO.add(user.getId_uzytkownik(), advertisement);
            model.addObject("css", "msgSuccess");
            model.addObject("msg", "Dodano poprawnie!");
            fillListBox(model);

            advertisement.setLokalizacja("");
            advertisement.setTytul("");
            advertisement.setOpis("");
            advertisement.setZarobki(0);

            return model;
        }
        return model;
        }
}
