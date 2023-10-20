package fr.loicmathieu.dojo.quarkus.messaging.search;

public record EnhancedOrder(
        int nbItem,
        int totalPrice,
        String userId,
        String shippingAddress,
        String userFirstName,
        String userLastName) {
    public EnhancedOrder {
    }
}



