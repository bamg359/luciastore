package storeapp.repository;

import storeapp.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    List<Person> persons = new ArrayList<>();

    public Person savePerson(Person person){
        persons.add(person);
        return person;
    }

    public List<Person> findAllPersons(){
        for (Person person: persons) {
            System.out.println(person.getId() + " " + person.getName() + " " + person.getLastName() + " " + person.getEmail() + " " + person.isStatus());
        }
        return persons;
    }

    public Person findPersonById(int id){
        System.out.println("repositorio: " + id);
        try{
            for(Person person: persons){
                if(person.getId() == id){
                    System.out.println(person.getId() + " " + person.getName() + " " + person.getLastName() + " " + person.getEmail() + " " + person.isStatus());
                }
                return person;
            }
            return null;
        }catch (Exception e){
            System.out.println("Persona no encontrada");
            return null;
        }
    }

    public Person updatePerson(int id){
        for(Person person: persons){
            if(id == person.getId()){
                return person;
            }
        }
        return null;
    }

    public void deletePerson(int id){
        for(Person person: persons){
            if(id == person.getId()){
                persons.remove(id);
            }
        }
    }
}

