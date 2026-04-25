package storeapp.domain.enums;

public enum OrderStatus {

    PENDING ("Pendiente"),
    CONFIRMED ("Confirmado"),
    SHIPPED ("Enviado"),
    DELIVERED ("Entregado"),
    CANCELLED ("Cancelado");

    private final String description;

    OrderStatus(String description){
        this.description = description;
    }


    public String getDescription(){
        return description;
    }



}
