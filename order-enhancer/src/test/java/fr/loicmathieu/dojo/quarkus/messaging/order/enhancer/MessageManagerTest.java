package fr.loicmathieu.dojo.quarkus.messaging.order.enhancer;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


@QuarkusTest
@QuarkusTestResource( InMemoryChannelTestResource.class )
class MessageManagerTest {

    @Inject
    @Any
    InMemoryConnector connector;
    @BeforeAll
    public static void switchMyChannels() {
        InMemoryConnector.switchIncomingChannelsToInMemory( "orders" );
        InMemoryConnector.switchOutgoingChannelsToInMemory( "enhanced-orders" );
    }

    @Test
    void test() {
        // Retrieves the in-memory source to send message
        InMemorySource<Order> orders = connector.source( "orders" );
        // Retrieves the in-memory sink to check what is received
        InMemorySink<EnhancedOrder> results = connector.sink( "enhanced-orders" );

        // Send order
        Order order = new Order( 0, 0, "user1", null );
        orders.send( order );

        // Check you have receives the expected messages
        Assertions.assertEquals( 1, results.received()
                                           .size() );
    }

    @AfterAll
    public static void revertMyChannels() {
        InMemoryConnector.clear();
    }
}
