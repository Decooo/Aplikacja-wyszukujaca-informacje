package pl.projekt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.projekt.model.Users;

/**
 * Created by jakub on 20.11.2017.
 */
@Component
public class UsersValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Users.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
    Users users = (Users) target;

        if(users.getLogin().length() < 3){
            errors.rejectValue("login","SmallSize.registrationForm.login");
        }

        if(users.getHaslo().length() < 3){
            errors.rejectValue("haslo","SmallSize.registrationForm.haslo");
        }

    }
}
