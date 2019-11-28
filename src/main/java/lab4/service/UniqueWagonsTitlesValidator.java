package lab4.service;

import lab4.model.Wagon;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueWagonsTitlesValidator implements ConstraintValidator<UniqueWagonsTitles, Collection<Wagon>> {

    @Override
    public boolean isValid(Collection<Wagon> wagons, ConstraintValidatorContext constraintValidatorContext) {
        Set<String> titlesSet = new HashSet<>();
        for (String s : wagons.stream().map(Wagon::getTitle).collect(Collectors.toList())) {
            if (!titlesSet.add(s)) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("cannot contain wagons with the same titles")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
