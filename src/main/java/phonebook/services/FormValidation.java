package phonebook.services;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import phonebook.models.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FormValidation implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    private final String STRING_PATTERN = "[a-zA-Z]{3,30}";

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        ValidationUtils.rejectIfEmpty(errors, "firstName", "required.firstName");

        ValidationUtils.rejectIfEmpty(errors, "secondName", "required.secondName");


        if (!(person.getFirstName() != null && person.getFirstName().isEmpty())) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(person.getFirstName());
            if (!matcher.matches()) {
                errors.rejectValue("firstName", "firstName.containNonChar");
            }
        }

        if (!(person.getSecondName() != null && person.getSecondName().isEmpty())) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(person.getSecondName());
            if (!matcher.matches()) {
                errors.rejectValue("secondName", "secondName.containNonChar");
            }
        }
    }
}
