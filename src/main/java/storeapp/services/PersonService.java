package storeapp.services;

import storeapp.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public Person createPerson();
    public Person getPersonById(int id);
    public Optional<Person> getPersonByEmail(String email);
    public Person updatePerson(int id);
    public void deletePerson(int id);
    public List<Person> getAllPersons();
}

