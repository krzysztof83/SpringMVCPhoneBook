package phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import phonebook.models.Person;
import phonebook.repositories.PersonDAO;
import phonebook.services.FormValidation;
import phonebook.services.SearchValidation;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    public PersonDAO personDAO;


    @GetMapping("/contactList")
    public String contactList(Model model) {
        Iterable<Person> persons = personDAO.findAll();
        model.addAttribute("persons", persons);
        return "contactList";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        personDAO.delete(new Long(id));
        return "redirect:/contactList";
    }

    @GetMapping(value = "/person/create")
    public String createForm(Model model) {
        model.addAttribute("person", new Person());
        return "create";
    }

    @RequestMapping(value = "/person/create", method = RequestMethod.POST)
    public String saveForm(@Valid Person person, BindingResult result, ModelMap model) {
        FormValidation formValidation = new FormValidation();

        formValidation.validate(person, result);

        if (result.hasErrors()) {
            return "create";
        }

        person = (Person) result.getModel().get("person");
        personDAO.save(person);
        return "redirect:/contactList";
    }

    @GetMapping(value = "/person/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        Long idInLong = new Long(id);
        Person person = personDAO.findOne(idInLong);
        model.addAttribute("person", person);
        return "edit";
    }

    @RequestMapping(value = "/person/edit", method = RequestMethod.POST)
    public String editForm(@Valid Person person, BindingResult result, ModelMap model) {
        FormValidation formValidation = new FormValidation();

        formValidation.validate(person, result);

        if (result.hasErrors()) {
            return "edit";
        }

        person = (Person) result.getModel().get("person");
        personDAO.save(person);
        return "redirect:/contactList";
    }

    @GetMapping(value = "/persons/find")
    public String findForm(Model model) {
        model.addAttribute("person", new Person());
        List<Person> persons = new ArrayList<>();

        model.addAttribute("persons", persons);
        return "find";
    }

    @RequestMapping(value = "/persons/find", method = RequestMethod.POST)
    public String findForm(Person person, BindingResult result, ModelMap model) {

        List<Person> persons = new ArrayList<>();
        Iterable<Person> personsIterable = new ArrayList<>();

        SearchValidation searchValidation = new SearchValidation();
        searchValidation.validate(person, result);

        if (result.hasErrors()) {
            return "find";
        } else {

            person = (Person) result.getModel().get("person");

            if (person != null) {

                if (person.getHomePhone() == null) {
                    if (person.getBusinessPhone() == null) {
                        personsIterable = personDAO.findAllByAllParamWithoutPhone(person.getFirstName()
                                , person.getSecondName()
                                , person.getEmail());
                    } else {
                        personsIterable = personDAO.findAllByAllParamWithoutHomePhone(person.getFirstName()
                                , person.getSecondName()
                                , person.getBusinessPhone()
                                , person.getEmail());
                    }
                } else {
                    if (person.getBusinessPhone() == null) {
                        personsIterable = personDAO.findAllByAllParamWithoutBusinessPhone(person.getFirstName()
                                , person.getSecondName()
                                , person.getHomePhone()
                                , person.getEmail());
                    } else {
                        personsIterable = personDAO.findAllByAllParam
                                (person.getFirstName()
                                        , person.getSecondName()
                                        , person.getHomePhone()
                                        , person.getBusinessPhone()
                                        , person.getEmail());
                    }
                }

            }
            personsIterable.forEach(p -> persons.add(p));

            model.addAttribute("persons", persons);
            model.addAttribute("person", person);

            return "find";
        }
    }

}