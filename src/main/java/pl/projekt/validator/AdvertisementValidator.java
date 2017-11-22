package pl.projekt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.projekt.model.Advertisement;

/**
 * Created by jakub on 22.11.2017.
 */
@Component
public class AdvertisementValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Advertisement.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
    Advertisement advertisement = (Advertisement) target;
        if(advertisement.getTytul().length()<5){
            errors.rejectValue("tytul","NotEmpty.advertisementForm.tytul");
        }
    }
}
