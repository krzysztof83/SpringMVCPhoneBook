package phonebook.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity(name = "person")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private Long ID;

    @Column(name = "firstname")
    @Size(min = 3, max = 30,message = "{firstNameInvalid}")
    private String firstName;

    @Column(name = "secondname")
    @Size(min = 3, max = 30,message = "{secondNameInvalid}")
    private String secondName;

    @Column(name = "homephone")
    @Range(min = 100000000,max = 999999999,message = "{phoneInvaild}")
    private Long homePhone=null;

    @Column(name = "businessphone")
    @Range(min = 100000000,max = 999999999,message = "{phoneInvaild}")
    private Long businessPhone=null;

    @Column(name = "email")
    @Email
    private String email;


    public Person() {
    }

    public Person(String firstName, String secondName, Long homePhone, Long businessPhone, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.homePhone = homePhone;
        this.businessPhone = businessPhone;
        this.email = email;
    }

    public Long getID() {
        return ID;
    }

    public Person setID(Long ID) {
        this.ID = ID;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public Person setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public Long getHomePhone() {
        return homePhone;
    }

    public Person setHomePhone(Long homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public Long getBusinessPhone() {
        return businessPhone;
    }

    public Person setBusinessPhone(Long businessPhone) {
        this.businessPhone = businessPhone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

}
