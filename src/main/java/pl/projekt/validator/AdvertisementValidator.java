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

        if(advertisement.getTytul().length()>50){
            errors.rejectValue("tytul","tooLong.advertisementForm.tytul");
        }

        if(advertisement.getLokalizacja().length()<5){
            errors.rejectValue("lokalizacja","NotEmpty.advertisementForm.lokalizacja");
        }

        if(advertisement.getLokalizacja().length()>50){
            errors.rejectValue("lokalizacja","tooLong.advertisementForm.lokalizacja");
        }

        if(advertisement.getOpis().length()<5){
            errors.rejectValue("opis","NotEmpty.advertisementForm.opis");
        }

    }
}
