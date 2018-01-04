package pl.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.projekt.dao.*;
import pl.projekt.logic.CheckingSpam;
import pl.projekt.model.*;
import pl.projekt.util.FillListBox;
import pl.projekt.util.FillTables;
import pl.projekt.validator.AdvertisementValidator;

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
		if (target == null) {
			return;
		}
		System.out.println("target = " + target);
		if (target.getClass() == Advertisement.class) {
			dataBinder.setValidator(advertisementValidator);
		}
	}

	@RequestMapping(value = "/dodaj", method = RequestMethod.GET)
	public ModelAndView addAdvert() {
		ModelAndView model = new ModelAndView("newAdvertisement");
		model.addObject("advert", new Advertisement());
		FillListBox fillListBox = new FillListBox(categoryDAO, formOfEmploymentDAO, positionDAO);
		fillListBox.fillListBox(model);

		return model;
	}

	@RequestMapping(value = "/lista")
	public ModelAndView list(@RequestParam(value = "page", required = false) Integer page) {
		ModelAndView model = new ModelAndView("advertsList");
		if (null == page) {
			page = 1;
		}
		List<Advertisement> adverts = advertisementDAO.findAllPagintaion(page);
		model.addObject("adverts", adverts);

		List<Advertisement> allAdverts = advertisementDAO.findAll();
		int size = allAdverts.size();
		int startpage = (page - 5 > 0 ? page - 5 : 1);

		int endpage = size / 15 + 1;

		if (endpage > startpage + 10) {
			endpage = startpage + 10;
		}

		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("page", page);
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
		System.out.println("AdvertController.list");
		return model;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(Principal principal, ModelAndView m, @ModelAttribute("advert") Advertisement advertisement, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("newAdvertisement");
		FillListBox fillListBox = new FillListBox(categoryDAO, formOfEmploymentDAO, positionDAO);
		CheckingSpam checkingSpam = new CheckingSpam(advertisementDAO);
		Users user = usersDAO.findUser(principal.getName());
		if (bindingResult.hasErrors()) {
			fillListBox.fillListBox(model);

			model.addObject("css", "error");
			model.addObject("msg", "Nie wprowadzono wszystkich danych lub wprowadzono je niepoprawnie");
		} else if ("spam".equalsIgnoreCase(checkingSpam.checkingSpam(user.getId_uzytkownik(), advertisement))) {
			model.addObject("css", "error");
			model.addObject("msg", "Nie można dodać ogłoszenia. Podobne znajduję się już w systemie");

		} else {
			advertisementDAO.add(user.getId_uzytkownik(), advertisement);
			model.addObject("css", "msgSuccess");
			model.addObject("msg", "Dodano poprawnie!");
			fillListBox.fillListBox(model);

			advertisement.setLokalizacja("");
			advertisement.setTytul("");
			advertisement.setOpis("");
			advertisement.setZarobki(0);

			return model;
		}
		return model;
	}

}
