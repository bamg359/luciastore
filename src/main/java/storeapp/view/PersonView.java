package storeapp.view;

import storeapp.services.PersonService;
import storeapp.utils.CustomerFormValidation;

public class PersonView {

    private final PersonService personService;

    public PersonView(PersonService personService){
        this.personService = personService;
    }

    public void createPerson(){
        personService.createPerson();
    }

    public void getPersonById(int id){
        personService.getPersonById(id);
    }

    public void updatePerson(){
        System.out.println("Estoy en la Vista de Persona");
        personService.updatePerson(CustomerFormValidation.validateInt("Ingrese el ID"));
    }

    public void deletePerson(){
        personService.deletePerson(CustomerFormValidation.validateInt("Ingrese el id de la Persona a eliminar"));
    }

    public void getAllPersons(){
        personService.getAllPersons();
    }
}

