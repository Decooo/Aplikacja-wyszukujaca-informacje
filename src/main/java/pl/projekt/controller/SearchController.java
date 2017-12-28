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
import pl.projekt.logic.SimpleLists;
import pl.projekt.model.*;
import pl.projekt.util.FillListBox;
import pl.projekt.util.FillTables;
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
    private String inquiry = "";
    private List<Advertisement> ads;


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
        setAds(adverts);
        setInquiry(inquiry);
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

        FillTables fillTables = new FillTables(categoryDAO, formOfEmploymentDAO, positionDAO, usersDAO);
        fillTables.fillTables(adverts, category, formOfEmployments, users, positions);

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
    public ModelAndView advancedSearch(@RequestParam(value = "inquiry") String inquiry,
                                       @ModelAttribute("search") Advertisement advertisement, @ModelAttribute("location") String location,
                                       @ModelAttribute("salary") String salary, @RequestParam("id_kategoria") int id_category,
                                       @RequestParam("id_forma_zatrudnienia") int id_formOfEmployment,
                                       @RequestParam("id_stanowisko") int id_position) {
        changeInquiry(inquiry);
        System.out.println("getAds().toString() = " + getAds().toString());
        ModelAndView model = new ModelAndView();
        List<Category> category = new ArrayList<Category>();
        List<FormOfEmployment> formOfEmployments = new ArrayList<FormOfEmployment>();
        List<Users> users = new ArrayList<Users>();
        List<Position> positions = new ArrayList<Position>();
        List<Advertisement> adverts = new ArrayList<Advertisement>();

        FillListBox fillListBox = new FillListBox(categoryDAO, formOfEmploymentDAO, positionDAO);
        fillListBox.fillListBox(model);
        SimpleLists simpleLists = new SimpleLists(advertisementDAO);
        adverts = simpleLists.searchSimpleList(getAds(), salary, location, id_category, id_position, id_formOfEmployment);
        model.addObject("adverts", adverts);

        FillTables fillTables = new FillTables(categoryDAO, formOfEmploymentDAO, positionDAO, usersDAO);
        fillTables.fillTables(adverts, category, formOfEmployments, users, positions);

        model.addObject("category", category);
        model.addObject("formOfEmployments", formOfEmployments);
        model.addObject("users", users);
        model.addObject("positions", positions);
        model.addObject("inquiry", inquiry);
        model.setViewName("searchList");
        return model;
    }

    private void changeInquiry(@RequestParam(value = "inquiry") String inquiry) {
        if (inquiry.equals("")) {
            setInquiry(inquiry);
            setAds(advertisementDAO.findAll());
        } else if (!inquiry.equals(getInquiry())) {
            setInquiry(inquiry);
            setAds(advertisementDAO.fullTextSearch(inquiry));
        }else setAds(advertisementDAO.fullTextSearch(inquiry));
    }


    private List<Advertisement> getAds() {
        return ads;
    }

    private void setAds(List<Advertisement> ads) {
        this.ads = ads;
    }

    private String getInquiry() {
        return inquiry;
    }

    private void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }

}
