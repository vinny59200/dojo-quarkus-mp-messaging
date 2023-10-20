package fr.loicmathieu.dojo.quarkus.messaging.order.enhancer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;

import java.util.HashMap;
import java.util.Map;

public class InMemoryChannelTestResource implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        // TODO Switch the channels to the in-memory connector
        Map<String, String> env = new HashMap<>();
        env.putAll( InMemoryConnector.switchIncomingChannelsToInMemory( "orders" ) );
        env.putAll( InMemoryConnector.switchOutgoingChannelsToInMemory( "enhanced-orders" ) );
        return env;
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }

}
