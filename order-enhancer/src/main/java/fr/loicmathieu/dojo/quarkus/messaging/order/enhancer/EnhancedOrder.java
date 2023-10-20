package fr.loicmathieu.dojo.quarkus.messaging.order.enhancer;

public record EnhancedOrder(
        int nbItem,
        int totalPrice,
        String userId,
        String shippingAddress,
        String userFirstName,
        String userLastName) {

    public EnhancedOrder( Order order, User user ) {
        this( order.nbItem(),order.totalPrice(),order.userId(),order.shippingAddress(),user.firstName(),user.lastName());
    }
}


