package storeapp.services;

import storeapp.domain.Customer;
import storeapp.repository.CustomerRepository;
import storeapp.utils.CustomerFormValidation;

import java.util.Optional;

public class CustumerServiceImpl implements CustumerService {

    //Ahora vamos a comunicar las clases , para eso vamos a crear una instancia de la capa inmediatamente anterior
    private final CustomerRepository customerRepository;

    public CustumerServiceImpl( CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }



    @Override
    public Customer createCustomer() {

        Customer customer = new Customer();

        int id;
        while (true) {
            String prompt = "Ingrese el id del cliente";
            id = CustomerFormValidation.validateInt(prompt);
            if (customerRepository.findCustomerById(id) == null) {
                customer.setId(id);
                break;
            } else {
                System.out.println("❎ ID ya existe, ingrese un ID único");
            }
        }


        System.out.println("Ingrese el nombre del cliente");
        String name = CustomerFormValidation.validateString("Ingrese el nombre del cliente");
        customer.setName(name);

        System.out.println("Ingrese el apellido");
        String lastName = CustomerFormValidation.validateString("Ingrese el apellido");
        customer.setLastName(lastName);

        System.out.println("ingrese el email");
        String email = CustomerFormValidation.validateString("ingrese el email");
        customer.setEmail(email);

        System.out.println("Ingrese el password ");
        String password = CustomerFormValidation.validateString("Ingrese el password ");
        customer.setPassword(password);

        System.out.println("Estado Cliente ");

        customer.setStatus(CustomerStateSelector.selectCustomerState());

        System.out.println("Cupo");
        double quote = CustomerFormValidation.validateDouble("Cupo");
        customer.setQuote(quote);

        System.out.println("Tipo de Cliente");
        customer.setCustomerType(CustomerTypeSelector.selectTypeCustomer());

        return customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int id) {

        return customerRepository.findCustomerById(id);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return Optional.empty();
    }


    @Override
    public Customer updateCustomer(int id) {

        System.out.println("Estoy en el service");
        Customer customer = customerRepository.findCustomerById(id);

        if(customer != null){

            System.out.println("Actualizar 1. id 2. Nombre 3 Apellido 4.Correo 5. Contraseña");
            int option = CustomerFormValidation.validateInt("Opcion");

            switch (option){
                case 1:
                    int newId = CustomerFormValidation.validateInt("Actualizar id");
                    if (customerRepository.findCustomerById(newId) == null || newId == customer.getId()) {
                        customer.setId(newId);
                    } else {
                        System.out.println("❎ ID ya existe, no se actualizó");
                    }
                    break;
                case 2:
                    customer.setName(CustomerFormValidation.validateString("Actualzar nombre"));
                    break;
                case 3:
                    customer.setLastName(CustomerFormValidation.validateString("Actualizar Apellido"));
                    break;
                case 4:
                    customer.setEmail(CustomerFormValidation.validateString("Actualizar Email"));
                    break;
                case 5:
                    customer.setPassword(CustomerFormValidation.validateString("Actualizar contraseña"));
                default:
                    System.out.println("Seleccione una opcion");
                    break;
            }

            customerRepository.updateCustomer(id);
        }else{
            System.out.println("❎ Cliente  no encontrado");
        }



        return customer;
    }

    @Override
    public void deleteCustomer(int id) {

        customerRepository.deleteCustomer(id);

    }
}
