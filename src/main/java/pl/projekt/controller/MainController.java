package pl.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.projekt.dao.UsersDAO;
import pl.projekt.model.Users;
import pl.projekt.validator.UsersValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by jakub on 15.11.2017.
 */
@Controller
@Transactional
@EnableWebMvc
public class MainController {

    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private UsersValidator usersValidator;

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/403")
    public String accesDenied() {
        return "/403";
    }

    @RequestMapping(value = {"/accountInfo"}, method = RequestMethod.GET)
    public String accountInfo(Model model, Principal principal) {
        String username = principal.getName();
        System.out.println("Username::: " + username);
        return "accountInfo";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "login?logout";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public Model logon(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Niepoprawna nazwa uzytkownika lub haslo");
        }

        if (logout != null) {
            model.addAttribute("msg", "Wylogowano pomyslnie");
        }

        model.addAttribute("login");
        return model;
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public ModelAndView registration(ModelMap registration) {
        ModelAndView model = new ModelAndView("registration");
        registration.addAttribute("users", new Users());
        return model;
    }

    @InitBinder
    public void myInitBuilder(WebDataBinder dataBinder) {
        Object taget = dataBinder.getTarget();
        if (taget == null) {
            return;
        }
        System.out.println("target = " + taget);
        if (taget.getClass() == Users.class) {
            dataBinder.setValidator(usersValidator);
        }
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, ModelAndView m, @Validated Users users, BindingResult bindingResult) throws IOException {
        ModelAndView modelAndView = new ModelAndView("login");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("css", "error");
            modelAndView.addObject("msg", "Nie wprowadzono wszystkich danych lub wprowadzono je niepoprawnie!");
            modelAndView.setViewName("registration");
            return modelAndView;
        }

        usersDAO.add(users.getLogin(),users.getHaslo());
        modelAndView.addObject("css","msgSuccess");
        modelAndView.addObject("msg","Zarejestrowano poprawnie!");

        users.setLogin("");
        users.setHaslo("");

        return modelAndView;

    }

}
