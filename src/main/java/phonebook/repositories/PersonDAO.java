package phonebook.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import phonebook.models.Person;

@Repository
public interface PersonDAO extends CrudRepository<Person, Long> {

    public final String SELECT = "SELECT p FROM person p WHERE";
    public final String AND = " AND";
    public final String LIKE_FIRST_NAME = " p.firstName LIKE CONCAT('%',:firstName,'%')";
    public final String LIKE_SECOND_NAME = " p.secondName Like CONCAT('%',:secondName,'%')";
    public final String LIKE_HOME_PHONE = " p.homePhone LIKE CONCAT('%',:homePhone,'%')";
    public final String LIKE_BUSSINES_PHONE = " p.businessPhone LIKE CONCAT('%',:businessPhone,'%')";
    public final String LIKE_EMAIL = " p.email LIKE CONCAT('%',:email,'%')";

    public String myQueryWithAllParam = SELECT
            + LIKE_FIRST_NAME + AND
            + LIKE_SECOND_NAME + AND
            + LIKE_HOME_PHONE + AND
            + LIKE_BUSSINES_PHONE + AND
            + LIKE_EMAIL;

    public String myQueryWithoutHomePhone = SELECT
            + LIKE_FIRST_NAME + AND
            + LIKE_SECOND_NAME + AND
            + LIKE_BUSSINES_PHONE + AND
            + LIKE_EMAIL;

    public String myQueryWithoutBusinessPhone = SELECT
            + LIKE_FIRST_NAME + AND
            + LIKE_SECOND_NAME + AND
            + LIKE_HOME_PHONE + AND
            + LIKE_EMAIL;

    public String myQueryWithoutPhone = SELECT
            + LIKE_FIRST_NAME + AND
            + LIKE_SECOND_NAME
            + AND + LIKE_EMAIL;

    @Query(myQueryWithAllParam)
    public Iterable<Person> findAllByAllParam(@Param("firstName") String firstName
            , @Param("secondName") String secondName
            , @Param("homePhone") Long homePhone
            , @Param("businessPhone") Long businessPhone
            , @Param("email") String email);

    @Query(myQueryWithoutHomePhone)
    public Iterable<Person> findAllByAllParamWithoutHomePhone(@Param("firstName") String firstName
            , @Param("secondName") String secondName
            , @Param("businessPhone") Long businessPhone
            , @Param("email") String email);

    @Query(myQueryWithoutBusinessPhone)
    public Iterable<Person> findAllByAllParamWithoutBusinessPhone(@Param("firstName") String firstName
            , @Param("secondName") String secondName
            , @Param("homePhone") Long homePhone
            , @Param("email") String email);

    @Query(myQueryWithoutPhone)
    public Iterable<Person> findAllByAllParamWithoutPhone(@Param("firstName") String firstName
            , @Param("secondName") String secondName
            , @Param("email") String email);
}
