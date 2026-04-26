package storeapp.services;

import storeapp.domain.Person;
import storeapp.repository.PersonRepository;
import storeapp.utils.CustomerFormValidation;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PersonServiceImpl implements PersonService {

    Scanner sc = new Scanner(System.in);
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson() {

        Person person = new Person();

        String prompt = "Ingrese el id de la persona";
        person.setId(CustomerFormValidation.validateInt(prompt));

        System.out.println("Ingrese el nombre de la persona");
        String name = sc.nextLine();
        person.setName(name);

        System.out.println("Ingrese el apellido de la persona");
        String lastName = sc.nextLine();
        person.setLastName(lastName);

        System.out.println("Ingrese el email de la persona");
        String email = sc.nextLine();
        person.setEmail(email);

        System.out.println("Ingrese la contraseña de la persona");
        String password = sc.nextLine();
        person.setPassword(password);

        System.out.println("Estado de la Persona");
        person.setStatus(CustomerFormValidation.validateBoolean("Ingrese el estado (true/false)"));

        return personRepository.savePerson(person);
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.findPersonById(id);
    }

    @Override
    public Optional<Person> getPersonByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Person updatePerson(int id) {

        System.out.println("Estoy en el service de persona");
        Person person = personRepository.findPersonById(id);

        if(person != null && person.getId() == id){

            System.out.println("Actualizar 1. id 2. Nombre 3. Apellido 4. Email 5. Contraseña 6. Estado");
            int option = CustomerFormValidation.validateInt("Opcion");

            switch (option){
                case 1:
                    person.setId(CustomerFormValidation.validateInt("Actualizar id"));
                    break;
                case 2:
                    person.setName(CustomerFormValidation.validateString("Actualizar nombre"));
                    break;
                case 3:
                    person.setLastName(CustomerFormValidation.validateString("Actualizar apellido"));
                    break;
                case 4:
                    person.setEmail(CustomerFormValidation.validateString("Actualizar email"));
                    break;
                case 5:
                    person.setPassword(CustomerFormValidation.validateString("Actualizar contraseña"));
                    break;
                case 6:
                    person.setStatus(CustomerFormValidation.validateBoolean("Actualizar estado"));
                    break;
                default:
                    System.out.println("Seleccione una opción");
                    break;
            }

            personRepository.updatePerson(id);
        }else{
            System.out.println("Persona no encontrada");
        }

        return person;
    }

    @Override
    public void deletePerson(int id) {
        personRepository.deletePerson(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAllPersons();
    }
}

