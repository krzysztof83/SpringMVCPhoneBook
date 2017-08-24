package phonebook.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import phonebook.models.Person;
import phonebook.models.Role;
import phonebook.repositories.PersonDAO;
import phonebook.repositories.RoleRepository;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private PersonDAO personDAO;
    private RoleRepository roleRepository;

    @Autowired
    public Bootstrap(PersonDAO personDAO, RoleRepository roleRepository) {
        this.personDAO = personDAO;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role role = new Role();
        role.setId(1);
        role.setRole("ADMIN");
        roleRepository.save(role);

        Person person1 = new Person("Jan", "Kowalski"
                , 123456789L, 987234524L, "Asdw@wp.pl");

        Person person2 = new Person("Maciej", "Muszka"
                , 458764356L, 253746234L, "mamaciej@wp.pl");

        Person person3 = new Person("Tomasz", "Nowak"
                , 234435546L, 123645434L, "tomko@poczta.pl");

        Person person4 = new Person("Anna", "Kowalczyk"
                , 123645434L, 123456789L, "annnna@interia.pl");

        Person person5 = new Person("Karol", "Jankowski "
                , 253746234L, 234435546L, "karjan@wp.pl");

        personDAO.save(person1);
        personDAO.save(person2);
        personDAO.save(person3);
        personDAO.save(person4);
        personDAO.save(person5);


    }
}
