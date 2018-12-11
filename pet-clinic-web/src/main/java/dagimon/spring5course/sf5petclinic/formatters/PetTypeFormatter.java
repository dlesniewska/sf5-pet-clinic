package dagimon.spring5course.sf5petclinic.formatters;

import dagimon.spring5course.sf5petclinic.model.PetType;
import dagimon.spring5course.sf5petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }


    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> types = petTypeService.findAll();
        for(PetType type : types) {
            if(type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("PetType not found: " + text, 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
