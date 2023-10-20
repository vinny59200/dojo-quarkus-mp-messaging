package fr.loicmathieu.dojo.quarkus.messaging.order.enhancer;

public record Order(int nbItem, int totalPrice, String userId, String shippingAddress) {
    public Order {
    }
}
